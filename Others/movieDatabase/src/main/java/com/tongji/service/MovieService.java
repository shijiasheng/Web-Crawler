package com.tongji.service;


import com.tongji.dto.SearchInfo;
import com.tongji.model.Actor;
import com.tongji.model.Director;
import com.tongji.model.Movie;

import java.util.List;

public interface MovieService
{
    List<Movie> searchMovie(SearchInfo searchInfo);


    List<Director> directors(Integer pageNum, Integer pageSize, String name);

    List<Actor> actors(Integer pageNum, Integer pageSize, String name);
}
