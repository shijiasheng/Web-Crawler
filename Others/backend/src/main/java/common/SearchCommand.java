package common;

import lombok.Data;

import java.util.List;

/**
 * @author cheng fu
 * @Description TODO
 * @date 2021/1/1-22:39
 */
@Data
public class SearchCommand
{
//    年
//    月
//    日
//    星期
//    季度
//    导演
//    主演
//    参演
//    电影类别
//    评分
    String year;
    String month;
    String day;
    String week;
    List<String> months;
    String director;
    String actor;
    String is_supporting;
    String genre;
    String star;
    int limit;
    int skip;
    int searchQuarter;
}
