package edu.tongji.nep4jbackend.entities;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.register.Register;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public class GenresTest {

    @Id
    @GeneratedValue
    private Long id;

    @Property("num")
    private Integer num;

    @Property("title")
    private String title;

    @Property("titles")
    private List<String> titles;

    public GenresTest() {
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public GenresTest(Integer num, String title, List<String> titles) {
        this.num = num;
        this.title = title;
        this.titles = titles;
    }

    public GenresTest withId(Long id) {
        if (this.id.equals(id)) {
            return this;
        } else {
            GenresTest newObject = new GenresTest(this.num,this.title,this.titles);
            newObject.id = id;
            return newObject;
        }
    }
    @Override
    public String toString() {
        return "GenresTest{" +
                "num=" + num +
                ", titles=" + titles +
                '}';
    }
}
