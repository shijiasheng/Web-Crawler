import com.tongji.MyApplication;
import com.tongji.mapper.ReviewMapper;
import com.tongji.model.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class)
public class Main {
    @Autowired
    private ReviewMapper reviewMapper;


    /**
     * 写入revie表
     */
    @Test
    public void readFile() {
        String pathname = "C:/Users/12549/Desktop/大三上/8.数据仓库/movies.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            //每个评论8行
            int index = 0;
            int update = 0;

            String productId = "product/productId: ";
            String userId = "review/userId: ";
            String profileName = "review/profileName: ";
            String helpfulness = "review/helpfulness: ";
            String score = "review/score: ";
            String time = "review/time: ";
            String summary = "review/summary: ";
            String text = "review/text: ";

            String productIdText = "productId";
            String userIdText = "userId";
            String profileNameText = "profileName";
            String helpfulnessText = "helpfulness";
            String scoreText = "score";
            String timeText = "time";
            String summaryText = "summary";
            String textText = "text";

//            System.out.println(productIdText+","+userIdText+","+profileNameText+
//                    ","+helpfulnessText+","+scoreText+","+timeText+","+summaryText+","+textText);
            while ((line = br.readLine()) != null) {
                System.out.println(update++);
                // 一次读入一行数据

                if (line.startsWith(productId)) {
                    productIdText = line.replaceFirst(productId, "");
                }
                else if (line.startsWith(userId)) {
                    userIdText = line.replaceFirst(userId, "");
                }
                else if (line.startsWith(profileName)) {
                    profileNameText = line.replaceFirst(profileName, "");
                }
                else if (line.startsWith(helpfulness)) {
                    helpfulnessText = line.replaceFirst(helpfulness, "");
                }
                else if (line.startsWith(score)) {
                    scoreText = line.replaceFirst(score, "");
                }
                else if (line.startsWith(time)) {
                    timeText = line.replaceFirst(time, "");
                }
                else if (line.startsWith(summary)) {
                    summaryText = line.replaceFirst(summary, "");
                }
                else if (line.startsWith(text)) {
                    textText = line.replaceFirst(text, "");
                }

                //读取完一条评论
                if (++index == 8) {
//                    System.out.println(productIdText+"##"+userIdText+"##"+profileNameText+
//                            "##"+helpfulnessText+"##"+scoreText+"##"+timeText+"##"+summaryText+"##"+textText);
                    Review review = new Review();
                    review.setProductId(productIdText);
                    review.setUserId(userIdText);
                    review.setProfileName(profileNameText);
                    review.setHelpfulness(helpfulnessText);
                    review.setScore(scoreText);
                    review.setTime(timeText);
                    review.setSummary(summaryText);
                    review.setText(textText);
                    reviewMapper.insertSelective(review);
                    index = 0;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入TXT文件
     */
    public static void writeFile() {
        try {
            File writeName = new File("C:\\Users\\12549\\Desktop\\output.csv"); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write("我会写入文件啦1\r\n"); // \r\n即为换行
                out.write("我会写入文件啦2\r\n"); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

