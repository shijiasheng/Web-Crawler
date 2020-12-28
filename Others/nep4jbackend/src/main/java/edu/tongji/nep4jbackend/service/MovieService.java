package edu.tongji.nep4jbackend.service;

import edu.tongji.nep4jbackend.entities.MovieEntity;
import edu.tongji.nep4jbackend.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    private final static Logger LOG = LoggerFactory.getLogger(MovieService.class);

    private final MovieRepository movieRepository;


    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional(readOnly = true)
    public MovieEntity findById(String id) {
        MovieEntity result = movieRepository.findByid(id);
        return result;
    }
}
