package com.tongji.service;


import com.tongji.dto.SearchInfo;
import com.tongji.model.*;

import java.util.List;
import java.util.Map;

public interface MovieService
{
    Map<String,Object> searchMovie(SearchInfo searchInfo);


    List<Director> directors(Integer pageNum, Integer pageSize, String name);

    List<Actor> actors(Integer pageNum, Integer pageSize, String name);

    List<Genre> genre(Integer pageNum, Integer pageSize, String name);

    List<Time> times(Integer pageNum, Integer pageSize, Integer year, Integer month, Integer day, Integer week, List<Integer> quarterList);

    Movie detail(Integer id);

    List<Map<String, Object>> getReview(Integer movieId);

    Map<String, Integer> actorToDirector(String actorName);

    Map<String, Integer> directorToActor(String directorName);

    Map<String, Integer> actorToActor(String actorName);
}
