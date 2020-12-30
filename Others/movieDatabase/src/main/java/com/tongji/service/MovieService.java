package com.tongji.service;


import com.tongji.dto.SearchInfo;
import com.tongji.model.*;

import java.util.List;

public interface MovieService
{
    List<Movie> searchMovie(SearchInfo searchInfo);


    List<Director> directors(Integer pageNum, Integer pageSize, String name);

    List<Actor> actors(Integer pageNum, Integer pageSize, String name);

    List<Genre> genre(Integer pageNum, Integer pageSize, String name);

    List<Time> times(Integer pageNum, Integer pageSize, Integer year, Integer month, Integer day, Integer week, List<Integer> quarterList);
}
