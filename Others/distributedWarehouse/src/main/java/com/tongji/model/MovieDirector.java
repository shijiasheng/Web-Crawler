package com.tongji.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class MovieDirector implements Serializable {
    private Integer movieId;

    private Integer directorId;

    private static final long serialVersionUID = 1L;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Integer directorId) {
        this.directorId = directorId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", movieId=").append(movieId);
        sb.append(", directorId=").append(directorId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}