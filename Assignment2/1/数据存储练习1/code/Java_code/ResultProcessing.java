import java.io.*;
import java.util.Arrays;

public class ResultProcessing
{

    //对分词结果进行换行
    public static void test() throws FileNotFoundException
    {
        System.setOut(new PrintStream(new BufferedOutputStream(
                new FileOutputStream("C:\\Users\\12549\\Desktop\\review\\file1.txt")), true));
        String fileName = "C:\\Users\\12549\\Desktop\\review\\output.txt";
        File file = new File(fileName);
        InputStream in = null;
        int count = 0;
        try
        {
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1)
            {
                if (tempbyte == 32)
                {
                    ++count;
                }

                System.out.write(tempbyte);
                if (count == 1000)
                {
                    System.out.println();
                    count = 0;
                }
            }
            in.close();
        }
//        try
//        {
//            // 一次读多个字节
//            byte[] tempbytes = new byte[10000000];
//            int index = 0;
//            int byteread = 0;
//            in = new FileInputStream(fileName);
//            // 读入多个字节到字节数组中，byteread为一次读入的字节数
//            while ((byteread = in.read(tempbytes)) != -1)
//            {
//
//                System.out.write(tempbytes, 0, byteread);
////                System.out.println(index++);
//            }
//        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
        finally
        {
            if (in != null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e1)
                {
                }
            }
        }
    }

    //对hadoop得到的第一次结果(没有降序的)进行一些简单处理
    public static void processResult()
    {
        try
        {
            //控制台到文件
            //重定向
            //加一个true相当于flush
            System.setOut(new PrintStream(new BufferedOutputStream(
                    new FileOutputStream("C:\\Users\\12549\\Desktop\\result\\output1.txt")), true));

            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\12549\\Desktop\\result\\output.txt"));
            int index = 0;//一行1000
            int line = 0;//看看处理到了多少行
            String str;


            while ((str = in.readLine()) != null)
            {
                String[] s = str.split("[\\W]");
//                System.out.println(Arrays.toString(s));
                if(s[0].startsWith("zz"))
                {
                    continue;
                }
                if(s[0].matches("_*"))
                {
                    continue;
                }
                //把包含_的单词给去掉_
                if(s[0].contains("_"))
                {
                    s[0]=s[0].replaceAll("_", "");
                }
                //有些单词去掉_后只有一个字符
                if(s[0].length()<=1)
                {
                    continue;
                }
                System.out.println(s[0]+" "+s[1]);
            }
        }
        catch (IOException e)
        {
        }
    }

    //对hadoop得到的降序结果进行进一步处理,得到最终结果
    public static void processResult2()
    {
        try
        {
            //控制台到文件
            //重定向
            //加一个true相当于flush
            System.setOut(new PrintStream(new BufferedOutputStream(
                    new FileOutputStream("C:\\Users\\12549\\Desktop\\result\\output3.txt")), true));

            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\12549\\Desktop\\result\\output2.txt"));
            int index = 0;//一行1000
            int line = 0;//看看处理到了多少行
            String str;


            while ((str = in.readLine()) != null)
            {
                String[] s = str.split("[\\W]");
                if(Integer.parseInt(s[1])<10)
                {
                    continue;
                }
                System.out.println(s[0]+" "+s[1]);
            }
        }
        catch (IOException e)
        {
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
//        test();
//        processResult();
        processResult2();
    }


}
