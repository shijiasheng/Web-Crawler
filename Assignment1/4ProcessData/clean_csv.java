import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class clean_csv {
    public static void main(String[] args) throws IOException {
        List<String> a_line=new ArrayList<>();
        List<String> final_line=new ArrayList<>();
        File csv = new File("C:\\Users\\Lenovo\\Desktop\\datawarehouse_productData.csv");  // CSV文件路径
        File outFile = new File("C:\\Users\\Lenovo\\Desktop\\out.csv");
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(csv));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        String line = "";
        String everyLine = "";
        try {
            List<String> allString = new ArrayList<>();
            while ((line = br.readLine()) != null)  //读取到的内容给line变量
            {
                everyLine = line;

                //System.out.println(everyLine);
                allString.add(everyLine);
            }
            a_line=allString;
            //System.out.println("csv表格中所有行数："+allString.size());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        String temp1=a_line.get(0);
        for (int i = 0; i < a_line.size(); i++) {
            System.out.println("第"+i+"轮");
            String temp2=a_line.get(i);

            String[] temp1_list=temp1.split(",");
            System.out.println(temp1_list[1]);

            String[] temp2_list=temp2.split(",");
            System.out.println(temp2_list[1]);

            if(levenshtein(temp1_list[1],temp2_list[1])>0.4)
                continue;
            final_line.add(temp1);
            temp1=temp2;
        }
        final_line.add(temp1);
        for (int i = 0; i < final_line.size(); i++) {
            System.out.println(final_line.get(i));
        }
        for (int i = 0; i < final_line.size(); i++) {
            System.out.println("第"+i+"轮");
            writer.write(final_line.get(i));
            writer.newLine();
        }
        System.out.println(final_line.size());
    }

    //相似度匹配算法
    public static float levenshtein(String str1,String str2) {
        //计算两个字符串的长度。
        int len1 = str1.length();
        int len2 = str2.length();
        //建立上面说的数组，比字符长度大一个空间
        int[][] dif = new int[len1 + 1][len2 + 1];
        //赋初值，步骤B。
        for (int a = 0; a <= len1; a++) {
            dif[a][0] = a;
        }
        for (int a = 0; a <= len2; a++) {
            dif[0][a] = a;
        }
        //计算两个字符是否一样，计算左上的值
        int temp;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    temp = 0;
                } else {
                    temp = 1;
                }
                //取三个值中最小的
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,
                        dif[i - 1][j] + 1);
            }
        }
        //System.out.println("字符串\""+str1+"\"与\""+str2+"\"的比较");
        //取数组右下角的值，同样不同位置代表不同字符串的比较
        //System.out.println("差异步骤："+dif[len1][len2]);
        //计算相似度
        float similarity =1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
        //System.out.println("相似度："+similarity);
        return similarity;
    }

    //得到最小值
    private static int min(int... is) {
        int min = Integer.MAX_VALUE;
        for (int i : is) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }
}
