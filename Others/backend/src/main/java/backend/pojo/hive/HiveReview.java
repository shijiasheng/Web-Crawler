package backend.pojo.hive;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class HiveReview {
    private String productID;

    private String userID;

    private String profileName;

    private String helpfulness;

    private String time;

    private String summary;

    private String text;

    private boolean sentiment;

    private Double score;

    private String title;

    private String suffix;
}
