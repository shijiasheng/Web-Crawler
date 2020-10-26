package com.tongji.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Backup1 implements Serializable {
    private String id;

    private String title;

    private String releaseDate;

    private String genres;

    private String director;

    private String producers;

    private String actor;

    private String supportingActors;

    private String mediaFormat;

    private String runTime;

    private String mpaaRating;

    private String subtitles;

    private String studio;

    private String itemModelNumber;

    private String dateFirstAvailable;

    private String imdb;

    private String audioLanguages;

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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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

    public String getProducers() {
        return producers;
    }

    public void setProducers(String producers) {
        this.producers = producers;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getSupportingActors() {
        return supportingActors;
    }

    public void setSupportingActors(String supportingActors) {
        this.supportingActors = supportingActors;
    }

    public String getMediaFormat() {
        return mediaFormat;
    }

    public void setMediaFormat(String mediaFormat) {
        this.mediaFormat = mediaFormat;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getItemModelNumber() {
        return itemModelNumber;
    }

    public void setItemModelNumber(String itemModelNumber) {
        this.itemModelNumber = itemModelNumber;
    }

    public String getDateFirstAvailable() {
        return dateFirstAvailable;
    }

    public void setDateFirstAvailable(String dateFirstAvailable) {
        this.dateFirstAvailable = dateFirstAvailable;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getAudioLanguages() {
        return audioLanguages;
    }

    public void setAudioLanguages(String audioLanguages) {
        this.audioLanguages = audioLanguages;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", releaseDate=").append(releaseDate);
        sb.append(", genres=").append(genres);
        sb.append(", director=").append(director);
        sb.append(", producers=").append(producers);
        sb.append(", actor=").append(actor);
        sb.append(", supportingActors=").append(supportingActors);
        sb.append(", mediaFormat=").append(mediaFormat);
        sb.append(", runTime=").append(runTime);
        sb.append(", mpaaRating=").append(mpaaRating);
        sb.append(", subtitles=").append(subtitles);
        sb.append(", studio=").append(studio);
        sb.append(", itemModelNumber=").append(itemModelNumber);
        sb.append(", dateFirstAvailable=").append(dateFirstAvailable);
        sb.append(", imdb=").append(imdb);
        sb.append(", audioLanguages=").append(audioLanguages);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}