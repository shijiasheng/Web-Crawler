package com.tongji.service;


import com.tongji.dto.SearchInfo;
import com.tongji.model.Movie;

import java.util.List;

public interface MovieService
{
    List<Movie> searchMovie(SearchInfo searchInfo);
}
