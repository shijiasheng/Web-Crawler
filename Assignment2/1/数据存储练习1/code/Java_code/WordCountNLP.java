
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class WordCountNLP
{

    //自定义的mapper，继承org.apache.hadoop.mapreduce.Mapper
    //并在这个类的继承类中至少自定义实现 Map() 方法
    public static class TokenizerMapper
            extends Mapper<Object, Text, Text, IntWritable>
    {
        //IntWritable相当于java中Integer整型变量，
        //定义了一个常量one,赋值为1
        private final static IntWritable one = new IntWritable(1);
        //Text中有length和bytes， length是获取字符转换为字节的长度
        //类似String
        private Text word = new Text();

        //        context它是mapper的一个内部类，简单的说顶级接口是为了在map或是reduce任务中跟踪task的状态，
//        很自然的MapContext就是记录了map执行的上下文，
//        在mapper类中，这个context可以存储一些job conf的信息，比如job运行时参数等
        /*
            key： 输入数据在原数据中的偏移量。
            value：具体的数据数据，此处为一段字符串。
            context：用于暂时存储 map() 处理后的结果。

            该方法只是分词,对应与作业中就是想办法把所有连在一起的单词都拆分,所以只需要考虑做一个良好的正则表达式分词符即可
            如:review/helpfulness: 7/7  应该分割未review helpfulness 7
         */
        public void map(Object key, Text value, Context context
        ) throws IOException, InterruptedException
        {
            try
            {
                int index = 0;//一行1000
                int line = 0;//看看处理到了多少行
                String str = value.toString();
                //依据非字符进行划分
                String regStr = "[\\W]";
                String[] s = str.split(regStr);
                String CONTAIN_DIGIT_REGEX = ".*[0-9].*";
                //删掉介词
                String[] prepositions = {"of", "the", "and", "in", "a", "to", "as", "s", "for", "on", "with", "out"}; //介词表
                StringBuilder nlpText = new StringBuilder();
                for (int i = 0; i < s.length; i++)
                {
                    //去掉空字符串,只有一个字符的字符串,含有数字的字符串
                    //剩下的就认为是单词了
                    //虽然不太准确,但是考虑到文件很大,如果导入英文词典来一个个比对,那么会很慢
                    //判断是否为介词
                    if (s[i].length() <= 1 || s[i].matches(CONTAIN_DIGIT_REGEX) || Arrays.asList(prepositions).contains(s[i]))
                    {
                        continue;
                    }
                    //单词全部归为小写,也不会漏掉,因为之后也会统计
                    s[i] = s[i].toLowerCase();

                    /*//just for test
                    word.set(s[i]);
                    context.write(word, one);*/

                    //把预处理的1000个单词交给NLP进行处理
                    nlpText.append(s[i]);
                    nlpText.append(" ");
                    ++index;
//                    System.out.print(s[i]+" ");//输出到文件中
                    //进行一次NLP处理
                    if (index == 1000)
                    {
                        index = 0;
                        ++line;
                        nlp(context, line, nlpText);
                        //处理完这轮后清空继续
                        nlpText = new StringBuilder();
                    }
                }
                //如果是最后一次,也要进行一次NLP处理,不然会漏掉
                nlp(context, line, nlpText);
            }
            catch (IOException e)
            {
            }

        }

        private void nlp(Mapper<Object, Text, Text, IntWritable>.Context context, int line, StringBuilder nlpText) throws IOException, InterruptedException
        {
            System.out.println(line);
            Properties props = new Properties();
            props.put("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");    // 七种Annotators
            StanfordCoreNLP pipeline = new StanfordCoreNLP(props);    // 依次处理

            //利用上面得到的一行文本,进行处理
            String text = nlpText.toString();
            Annotation document = new Annotation(text);    // 利用text创建一个空的Annotation
            pipeline.annotate(document);                   // 对text执行所有的Annotators（七种）

            // 下面的sentences 中包含了所有分析结果，遍历即可获知结果。
            List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

            for (CoreMap sentence : sentences)
            {
                for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class))
                {
                    //获取还原后的结果
                    //输出到文件中
                    //System.out.println(token.get(CoreAnnotations.LemmaAnnotation.class));
                    //System.out.println(s[i]);
                    //就是把读取到的字符串赋值给word
                    word.set(token.get(CoreAnnotations.LemmaAnnotation.class));
                    //每个单词存储一次
//                把字符串中的每个单词切分成< key, value >对的形式，如：< hello , 1>、< world, 1>
                    context.write(word, one);
                }
            }
        }
    }
//Reduce类也是继承自MapReduceBase的，需要实现Reducer接口。
// Reduce类以map的输出作为输入，因此Reduce的输入类型是<Text，Intwritable>
//    Reduce的输出是单词和它的数目
//    它的输出类型是<Text,IntWritable>
//    reduce函数将输入的key值作为输出的key值，然后将获得多个value值加起来，作为输出的值。

    /**
     * KEYIN：对应mapper阶段输出的key类型
     * VALUEIN：对应mapper阶段输出的value类型
     * KEYOUT：reduce处理完之后输出的结果kv对中key的类型
     * VALUEOUT：reduce处理完之后输出的结果kv对中value的类型
     */
    public static class IntSumReducer
            extends Reducer<Text, IntWritable, Text, IntWritable>
    {
        //某个单词的结果
        private IntWritable result = new IntWritable();

//        重写其reduce方法
//        Map过程输出<key,values>中key为单个单词，而values是对应单词的计数值所组成的列表，
//        Map的输出就是Reduce的输入，所以reduce方法只要遍历values并求和，即可得到某个单词的总次数。

        /**
         * reduce方法提供给reduce task进程来调用
         * <p>
         * reduce task会将shuffle阶段分发过来的大量kv数据对进行聚合，聚合的机制是相同key的kv对聚合为一组
         * 然后reduce task对每一组聚合kv调用一次我们自定义的reduce方法
         * 比如：<hello,1><hello,1><hello,1><tom,1><tom,1><tom,1>
         * hello组会调用一次reduce方法进行处理，tom组也会调用一次reduce方法进行处理
         * 调用时传递的参数：
         * key：一组kv中的key
         * values：一组kv中所有value的迭代器
         */
        public void reduce(Text key, Iterable<IntWritable> values,
                           Context context
        ) throws IOException, InterruptedException
        {
            //定义一个计数器
            int sum = 0;
            //通过value这个迭代器，遍历这一组kv中所有的value，进行累加
            for (IntWritable val : values)
            {
                sum += val.get();
            }
            result.set(sum);
            //输出这个单词的统计结果
            context.write(key, result);
        }
    }

    //客户端代码，写完交给ResourceManager框架去执行
    public static void main(String[] args) throws Exception
    {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "word count");
        //打成jar执行
        job.setJarByClass(WordCountNLP.class);
        //使用自己定义的mapper类处理输入的数据,即最上面的静态内部类TokenizerMapper
        job.setMapperClass(TokenizerMapper.class);
        //Combiner是用reducer定义的,大多数情况下和reducer处理的是同一种逻辑
        job.setCombinerClass(IntSumReducer.class);
        //使用哪个reducer处理输入的数据,即使用我们自己定义的IntSumReducer
        job.setReducerClass(IntSumReducer.class);
        //定义map输出的数据类型
        // key是Text value是IntWritable
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //之后运行会运行hadoop jar ./jar/hadoop.jar WordCount input output
        //数据在第一个输入的参数里 即input
        FileInputFormat.addInputPath(job, new Path(args[0]));
        //数据输出到传入的第二个参数里 即output
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        ////交给yarn去执行，直到执行结束才退出本程序
        //根据执行结果决定正常还是异常退出
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
