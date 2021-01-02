package com.tongji.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class MovieActor implements Serializable {
    private Integer movieId;

    private Integer actorId;

    private Boolean isSupporting;

    private static final long serialVersionUID = 1L;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public Boolean getIsSupporting() {
        return isSupporting;
    }

    public void setIsSupporting(Boolean isSupporting) {
        this.isSupporting = isSupporting;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", movieId=").append(movieId);
        sb.append(", actorId=").append(actorId);
        sb.append(", isSupporting=").append(isSupporting);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}