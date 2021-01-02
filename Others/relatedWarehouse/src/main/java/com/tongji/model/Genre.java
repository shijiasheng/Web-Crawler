package com.tongji.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Genre implements Serializable {
    private Integer genreId;

    private String name;

    @ApiModelProperty(value = "该类型的电影集合,便于查询")
    private String movies;

    private static final long serialVersionUID = 1L;

    public Integer getGenreId() {
        return genreId;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovies() {
        return movies;
    }

    public void setMovies(String movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", genreId=").append(genreId);
        sb.append(", name=").append(name);
        sb.append(", movies=").append(movies);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}