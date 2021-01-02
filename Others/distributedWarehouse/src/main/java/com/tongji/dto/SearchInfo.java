package com.tongji.dto;

import java.util.List;


public class SearchInfo {
    //分页相关
    Integer pageNum;
    Integer pageSize;

    //时间查询
    Integer year;
    Integer month;
    Integer day;
    List<Integer> quarterList;
    Integer week;

    //电影名称 返回的是电影列表 支持模糊查询 版本前端从里面寻找
    String title;

    //导演 不支持模糊查询，但是应该提供一个模糊查询导演名称的接口
    String director;

    //演员 不支持模糊查询，但是应该提供一个模糊查询演员名称的接口
    String actor;

    //演员主演还是参演 默认是查询主演的电影
    boolean isStarring;

    //电影类别
    String genre;

    //评分筛选，默认找出大于0分的
    Double score;

    //用户评价中有正面评价的电影
    //默认不靠这个筛选
    boolean hasPositive;

    public SearchInfo() {
    }

    public SearchInfo(Integer pageNum, Integer pageSize, Integer year, Integer month, Integer day, List<Integer> quarterList, Integer week, String title, String director, String actor, boolean isStarring, String genre, Double score, boolean hasPositive) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.year = year;
        this.month = month;
        this.day = day;
        this.quarterList = quarterList;
        this.week = week;
        this.title = title;
        this.director = director;
        this.actor = actor;
        this.isStarring = isStarring;
        this.genre = genre;
        this.score = score;
        this.hasPositive = hasPositive;
    }

    @Override
    public String toString() {
        return "SearchInfo{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", quarterList=" + quarterList +
                ", week=" + week +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", actor='" + actor + '\'' +
                ", isStarring=" + isStarring +
                ", genre='" + genre + '\'' +
                ", score=" + score +
                ", hasPositive=" + hasPositive +
                '}';
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
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

    public List<Integer> getQuarterList() {
        return quarterList;
    }

    public void setQuarterList(List<Integer> quarterList) {
        this.quarterList = quarterList;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public boolean isStarring() {
        return isStarring;
    }

    public void setStarring(boolean starring) {
        isStarring = starring;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public boolean isHasPositive() {
        return hasPositive;
    }

    public void setHasPositive(boolean hasPositive) {
        this.hasPositive = hasPositive;
    }
}
