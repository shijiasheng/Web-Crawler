package backend.pojo.neo4j;


import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
@Data
public class Movie {

    @Property(name = "title")
    private String title;

    @Property(name = "actor")
    private String actor;

    @Property(name = "director")
    private String director;

    @Property(name = "genre")
    private String genre;

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

}
