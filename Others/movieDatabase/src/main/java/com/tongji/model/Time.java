package com.tongji.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Time implements Serializable {
    private Integer id;

    private Integer year;

    private Integer month;

    private Integer day;

    private String season;

    private String week;

    @ApiModelProperty(value = "存储该时间点的一系列电影集合")
    private String movie;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", day=").append(day);
        sb.append(", season=").append(season);
        sb.append(", week=").append(week);
        sb.append(", movie=").append(movie);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}