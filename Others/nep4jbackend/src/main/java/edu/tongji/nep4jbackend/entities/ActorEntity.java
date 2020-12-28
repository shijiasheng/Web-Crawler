package edu.tongji.nep4jbackend.entities;

import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@lombok.Data
@NoArgsConstructor
@Node("Actor")
public class ActorEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property("name")
    private String name;

    @Relationship(type = "ActIn", direction = Relationship.Direction.OUTGOING)
    private List<MovieEntity> actInMovies;

    public ActorEntity(String name) {
    }

    public ActorEntity withId(Long id) {
        if (this.id.equals(id)) {
            return this;
        } else {
            ActorEntity newObject = new ActorEntity(this.name);
            newObject.id = id;
            return newObject;
        }
    }
}
