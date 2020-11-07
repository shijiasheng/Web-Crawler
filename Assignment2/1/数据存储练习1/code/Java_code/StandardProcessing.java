import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class StandardProcessing
{
    public static void main(String[] args)
    {
        try
        {
            //控制台到文件
            //重定向
            //加一个true相当于flush
            System.setOut(new PrintStream(new BufferedOutputStream(
                    new FileOutputStream("C:\\Users\\12549\\Desktop\\review\\standard_review.txt")),true));

            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\12549\\Desktop\\review\\movies.txt"));
            int index = 0;//一行1000
            int line = 0;//看看处理到了多少行
            //依据非字符进行划分
            String regStr = "[\\W]";
            String str;

            //删掉介词
            String[] prepositions = {"of", "the", "and", "in", "a", "to", "as", "s", "for", "on", "with", "out"}; //介词表

            while ((str = in.readLine()) != null)
            {
                String[] s = str.split(regStr);
                for (int i = 0; i < s.length; i++)
                {
                    //单词全部归为小写,也不会漏掉,因为之后也会统计
                    s[i] = s[i].toLowerCase();

                    if (s[i].length() <= 1)
                    {
                        continue;
                    }
                    else if (s[i].matches(".*[0-9].*") || Arrays.asList(prepositions).contains(s[i]))
                    {
                        continue;
                    }

                    ++index;

                    System.out.print(s[i]+" ");//输出到文件中
                    if(index%1000==0)
                    {
                        System.out.println();
//                        System.out.println(line++);
                        index = 0;
                    }
                }
            }
        }
        catch (IOException e)
        {
        }
    }
}
