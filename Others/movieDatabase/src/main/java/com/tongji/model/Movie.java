package com.tongji.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable {
    private Integer movieId;

    private String productId;

    private Integer timeId;

    private String title;

    private String genres;

    private Integer runTime;

    @ApiModelProperty(value = "发布日期")
    private Date releaseDate;

    private Date dateFirstAvailable;

    @ApiModelProperty(value = "评分")
    private Long star;

    private String director;

    private String supportingActors;

    private String actor;

    private String linkId;

    private String linkTitle;

    private static final long serialVersionUID = 1L;

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
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

    public Long getStar() {
        return star;
    }

    public void setStar(Long star) {
        this.star = star;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
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

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", movieId=").append(movieId);
        sb.append(", productId=").append(productId);
        sb.append(", timeId=").append(timeId);
        sb.append(", title=").append(title);
        sb.append(", genres=").append(genres);
        sb.append(", runTime=").append(runTime);
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", dateFirstAvailable=").append(dateFirstAvailable);
        sb.append(", star=").append(star);
        sb.append(", director=").append(director);
        sb.append(", supportingActors=").append(supportingActors);
        sb.append(", actor=").append(actor);
        sb.append(", linkId=").append(linkId);
        sb.append(", linkTitle=").append(linkTitle);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}