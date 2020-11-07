
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
import java.util.StringTokenizer;

public class WordCountSimple
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
            StringTokenizer itr = new StringTokenizer(value.toString());
            while (itr.hasMoreTokens())
            {
                word.set(itr.nextToken());
                context.write(word, one);
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
        job.setJarByClass(WordCountSimple.class);
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
