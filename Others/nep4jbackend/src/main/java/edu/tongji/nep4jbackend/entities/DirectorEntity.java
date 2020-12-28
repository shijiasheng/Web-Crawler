package edu.tongji.nep4jbackend.entities;

import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
@NoArgsConstructor
@Node("Director")
public class DirectorEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property("name")
    private String name;

    @Relationship(type = "Direct", direction = Relationship.Direction.OUTGOING)
    private List<MovieEntity> directSomeMovies = new ArrayList<>();

    public DirectorEntity( String name) {
        this.name = name;
    }

    public DirectorEntity withId(Long id) {
        if (this.id.equals(id)) {
            return this;
        } else {
            DirectorEntity newObject = new DirectorEntity(this.name);
            newObject.id = id;
            return newObject;
        }
    }
}