package com.tongji.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {
    private String id;

    private String title;

    private String genres;

    private String director;

    private Integer runTime;

    @ApiModelProperty(value = "发布日期")
    private Date releaseDate;

    private Date dateFirstAvailable;

    private String supportingActors;

    private String actor;

    @ApiModelProperty(value = "同一个系列的电影,用字符串标识")
    private String series;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Date getDateFirstAvailable() {
        return dateFirstAvailable;
    }

    public void setDateFirstAvailable(Date dateFirstAvailable) {
        this.dateFirstAvailable = dateFirstAvailable;
    }

    public String getSupportingActors() {
        return supportingActors;
    }

    public void setSupportingActors(String supportingActors) {
        this.supportingActors = supportingActors;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", genres=").append(genres);
        sb.append(", director=").append(director);
        sb.append(", runTime=").append(runTime);
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", dateFirstAvailable=").append(dateFirstAvailable);
        sb.append(", supportingActors=").append(supportingActors);
        sb.append(", actor=").append(actor);
        sb.append(", series=").append(series);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}