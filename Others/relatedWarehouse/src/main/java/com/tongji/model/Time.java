package com.tongji.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class Time implements Serializable {
    private Integer timeId;

    private Integer year;

    private Integer month;

    private Integer day;

    private Integer week;

    @ApiModelProperty(value = "存储该时间点的一系列电影集合")
    private String movie;

    private static final long serialVersionUID = 1L;

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = timeId;
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

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
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
        sb.append(", timeId=").append(timeId);
        sb.append(", year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", day=").append(day);
        sb.append(", week=").append(week);
        sb.append(", movie=").append(movie);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}