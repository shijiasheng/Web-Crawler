package backend.pojo.neo4j;

import lombok.Data;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
@Data
/**
 * @author cheng fu
 * @Description TODO
 * @date 2021/1/1-9:49
 */
public class Neo4jMovie
{
    @Property(name = "product_id")
    private String product_id;

    @Property(name = "title")
    private String title;

    @Property(name = "time_id")
    private String time_id;

    @Property(name = "run_time")
    private String run_time;

    @Property(name = "star")
    private String star;

    @Property(name = "director")
    private String director;

    @Property(name = "supporting_actors")
    private String supporting_actors;

    @Property(name = "release_data")
    private String release_data;

}
