package backend.pojo.neo4j;


import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
@Data
public class MovieSimple {



    @Property(name = "title")
    private String title;

    @Property(name = "actor")
    private String actor;

    @Property(name = "director")
    private String director;

    @Property(name = "genre")
    private String genre;

}
