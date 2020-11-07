import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class NLPProcessing
{
    public static void main(String[] args)
    {
        try
        {
            //控制台到文件
            //重定向
            //加一个true相当于flush
//            System.setOut(new PrintStream(new BufferedOutputStream(
//                    new FileOutputStream("C:\\Users\\12549\\Desktop\\review\\standard_review.txt")),true));

            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\12549\\Desktop\\review\\movies.txt"));
            int index = 0;//一行1000
            int line = 0;//看看处理到了多少行
            //依据非字符进行划分
            String regStr = "[\\W]";
            String str;

            //删掉介词
            String[] prepositions = {"of", "the", "and", "in", "a", "to", "as", "s", "for", "on", "with", "out"}; //介词表
            StringBuilder nlpText = new StringBuilder();
            while ((str = in.readLine()) != null)
            {
                String[] s = str.split(regStr);
                for (int i = 0; i < s.length; i++)
                {
                    //单词全部归为小写,也不会漏掉,因为之后也会统计
                    s[i] = s[i].toLowerCase();

                    //把预处理的1000个单词交给NLP进行处理
                    nlpText.append(s[i]);
                    nlpText.append(" ");
                    ++index;
//                    System.out.print(s[i]+" ");//输出到文件中
                    //进行一次NLP处理
                    if (index == 200)
                    {
                        index = 0;
                        ++line;
//                        System.out.println(nlpText);
                        nlp(line, nlpText,prepositions);
                        System.out.println();
                        //处理完这轮后清空继续
                        nlpText = new StringBuilder();
                    }
                }
            }
            //如果是最后一次,也要进行一次NLP处理,不然会漏掉
            nlp(line, nlpText,prepositions);
        }
        catch (IOException e)
        {
        }
    }

    private static void nlp(int line, StringBuilder nlpText,String [] prepositions)
    {
//        System.out.println();
//        System.out.println(line);
//        System.out.println();
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
                //去掉空字符串,只有一个字符的字符串,含有数字的字符串
                //剩下的就认为是单词了
                //虽然不太准确,但是考虑到文件很大,如果导入英文词典来一个个比对,那么会很慢
                //判断是否为介词
                String result = token.get(CoreAnnotations.LemmaAnnotation.class);
                if (result.length() <= 1)
                {
                    continue;
                }
                else if (result.matches(".*[0-9].*") || Arrays.asList(prepositions).contains(result))
                {
                    continue;
                }

                System.out.print(result+" ");
//                System.out.println(s[i]);
                //就是把读取到的字符串赋值给word
                //每个单词存储一次
//                把字符串中的每个单词切分成< key, value >对的形式，如：< hello , 1>、< world, 1>
            }
        }
    }
}
