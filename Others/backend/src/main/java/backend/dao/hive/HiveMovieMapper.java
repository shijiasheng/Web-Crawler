package backend.dao.hive;

import backend.pojo.hive.HiveMovie;
import backend.pojo.hive.HiveReview;
import common.GetDTO.Command;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HiveMovieMapper {
//    @Autowired
//    @Qualifier("hiveJdbcTemplate")
//    private JdbcTemplate jdbcTemplate;

    public List<HiveMovie> getMoviesByHive(List<Command> commands,JdbcTemplate jdbcTemplate){
        StringBuffer sql = new StringBuffer("SELECT Product.*, Act.actor, Classify.genres, Direct.director, LanguageOf.language" +
                " FROM Product2 join Act2 join Classify2 join Direct2 join LanguageOf2" +
                " ON Product2.title = Act2.movie and Product2.title = Classify2.movie" +
                " and Product2.title = Direct2.movie and Product2.productId = LanguageOf2.productId" +
                " WHERE");
        for(Command m:commands){
            if (m.getOperator().equals("and")){
                sql.append(" and");
            }else if (m.getOperator().equals("or")){
                sql.append(" or");
            }

            switch (m.getField()) {
                case "title":
                    sql.append(" title");
                    break;
                case "directors":
                    sql.append(" director");
                    break;
                case "actors":
                    sql.append(" actor");
                    break;
                case "genre":
                    sql.append(" genres");
                    break;
                case "languages":
                    sql.append(" language");
                    break;
                case "binding":
                    sql.append(" binding");
                    break;
                case "running_time":
                    sql.append(" running_time");
                    break;
                case "release_year":
                    sql.append(" release_year");
                    break;
                case "release_month":
                    sql.append(" release_month");
                    break;
                case "release_day":
                    sql.append(" release_day");
                    break;
                case "release_weekday":
                    sql.append(" release_weekday");
                    break;
            }

            switch (m.getCondition()){
                case "eq":
                    sql.append("='"+m.getValue()+"'");
                    break;
                case "neq":
                    sql.append("!='"+m.getValue()+"'");
                    break;
                case "contains":
                    sql.append(" like '" + m.getValue() + "'");
                    break;
            }
        }

        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());
        List<HiveMovie> hiveMovieList = new ArrayList<>();
        for(Map<String,Object> m : list){
            HiveMovie hiveMovie = new HiveMovie();
            hiveMovie.setFormat((String)m.get("product2.binding"));
            hiveMovie.setProductId((String)m.get("product2.productId"));
            hiveMovie.setRate((String)m.get("product2.rate"));
            hiveMovie.setRelease_day((String)m.get("product2.release_day"));
            hiveMovie.setRelease_month((String)m.get("product2.release_month"));
            hiveMovie.setRelease_year((String)m.get("product2.release_year"));
            hiveMovie.setRelease_weekday((String)m.get("product2.release_weekday"));
            hiveMovie.setRuntime((String)m.get("product2.runtime"));
            hiveMovie.setTitle((String)m.get("product2.title"));
            hiveMovie.setActor((String) m.get("act2.actor"));
            hiveMovie.setDirector((String)m.get("direct2.director"));
            hiveMovie.setGenres((String)m.get("classify2.genres"));
            hiveMovie.setLanguage((String)m.get("languageof2.language"));
            hiveMovieList.add(hiveMovie);
        }

        return hiveMovieList;
    }

    public List<HiveReview> getReviewByProductID(String productID, JdbcTemplate jdbcTemplate){
        String suffix = productID.substring(productID.length()-1);
        StringBuffer sql = new StringBuffer("select * from ");
        List<HiveReview> hiveReviewList = new ArrayList<>();
        String tableName = "review1";
        Integer partNum = 0;
        if(suffix.compareTo("A")<0){
            partNum = 1;
        } else if (suffix.compareTo("I")<0){
            partNum = 2;
        }else if (suffix.compareTo("R")<0){
            partNum = 3;
        }else {
            partNum = 4;
        }
        sql.append(tableName + partNum.toString()+ " where suffix = '" + suffix + "' and productId = '"+ productID +"'");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql.toString());

        for(Map<String,Object> m : list){
            HiveReview h = new HiveReview();

            h.setProductID((String)m.get(tableName+partNum.toString()+".productId"));
            h.setHelpfulness((String)m.get(tableName+partNum.toString()+".helpfulness"));
            h.setProfileName((String)m.get(tableName+partNum.toString()+".profileName"));
            h.setScore((Double) m.get(tableName+partNum.toString()+".score"));
            h.setSentiment((Boolean) m.get(tableName+partNum.toString()+".sentiment"));
            h.setSummary((String)m.get(tableName+partNum.toString()+".summary"));
            h.setText((String)m.get(tableName+partNum.toString()+".text"));
            h.setTime((String) m.get(tableName+partNum.toString()+".time"));
            h.setUserID((String)m.get(tableName+partNum.toString()+".userID"));
            h.setTitle((String)m.get(tableName+partNum.toString()+".title"));
            h.setSuffix(suffix);

            hiveReviewList.add(h);
        }
        return hiveReviewList;
    }
}
