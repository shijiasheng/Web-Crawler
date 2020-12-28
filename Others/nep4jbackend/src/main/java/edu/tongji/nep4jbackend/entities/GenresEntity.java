package edu.tongji.nep4jbackend.entities;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

public class GenresEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property("name")
    private String name;

    @Property("num")
    private Integer num;

    @Property("title")
    private String title;

    @Relationship(type = "isgenres", direction = Relationship.Direction.INCOMING)
    private List<MovieEntity> isGenres = new ArrayList<>();

    public GenresEntity( String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MovieEntity> getIsGenres() {
        return isGenres;
    }

    public void setIsGenres(List<MovieEntity> isGenres) {
        this.isGenres = isGenres;
    }

    public GenresEntity withId(Long id) {
        if (this.id.equals(id)) {
            return this;
        } else {
            GenresEntity newObject = new GenresEntity(this.name);
            newObject.id = id;
            return newObject;
        }
    }
}
