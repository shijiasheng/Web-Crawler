package com.tongji.service.impl;

import com.tongji.dto.SearchInfo;
import com.tongji.model.Movie;
import com.tongji.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {


    @Override
    public List<Movie> searchMovie(SearchInfo searchInfo) {
        return null;
    }
}
