package edu.tongji.nep4jbackend.entities;

import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

import java.util.ArrayList;
import java.util.List;

@lombok.Data
@NoArgsConstructor
@Node("Movie")
public class MovieEntity {
    @Id
    private String id;

    @Property("IMDB")
    private String IMDB;

    @Property("subtitles")
    private String subtitles;

    @Property("release")
    private String release;

    @Property("length")
    private String length;

    @Property("itemModelNumber")
    private String itemModelNumber;

    @Property("runTime")
    private String runTime;

    @Property("title")
    private String title;

    @Property("dateFirstAvailable")
    private String dateFirstAvailable;

    @Relationship(type = "ActIn", direction = Direction.INCOMING)
    private List<ActorEntity> actorsAndRoles;

    @Relationship(type = "Direct", direction = Direction.INCOMING)
    private List<DirectorEntity> directorEntities = new ArrayList<>();

    @Relationship(type = "isgenres", direction = Direction.OUTGOING)
    private List<GenresEntity> genresEntities = new ArrayList<>();

    public MovieEntity(String id, String IMDB, String subtitles, String release, String length, String itemModelNumber, String runTime, String title, String dateFirstAvailable) {
        this.id = id;
        this.IMDB = IMDB;
        this.subtitles = subtitles;
        this.release = release;
        this.length = length;
        this.itemModelNumber = itemModelNumber;
        this.runTime = runTime;
        this.title = title;
        this.dateFirstAvailable = dateFirstAvailable;
    }
}
