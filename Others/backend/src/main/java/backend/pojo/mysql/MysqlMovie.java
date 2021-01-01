package backend.pojo.mysql;

import lombok.Data;

@Data
public class MysqlMovie {

    private String title;

    private String actor;

    private String director;

    private String genres;

    private String language;

    private String binding;

    private float rate;

    private int good_comment;

    private int total_comment;

    private float good_comment_rate;

    private int runtime;

    private int release_year;

    private int release_month;

    private int release_day;

    private int release_weekday;

    private String productId;

}