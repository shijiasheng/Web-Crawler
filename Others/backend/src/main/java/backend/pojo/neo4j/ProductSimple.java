package backend.pojo.neo4j;

import lombok.Data;
import org.neo4j.ogm.annotation.Property;

/**
 * @author: OY
 * @date: 14:26 2019/12/21
 */
@Data
public class ProductSimple {

    @Property(name = "language")
    private String language;

    @Property(name = "binding")
    private String binding;

    @Property(name = "rate")
    private String rate;

    @Property(name = "runtime")
    private String runtime;

    @Property(name = "release_year")
    private String release_year;

    @Property(name = "release_month")
    private String release_month;

    @Property(name = "release_day")
    private String release_day;

    @Property(name = "release_weekday")
    private String release_weekday;

    @Property(name = "productId")
    private String productId;

    private String good_comment;

    private String good_comment_rate;

    private String total_comment;
}