package edu.tongji.nep4jbackend.controller;

import edu.tongji.nep4jbackend.entities.GenresEntity;
import edu.tongji.nep4jbackend.entities.GenresTest;
import edu.tongji.nep4jbackend.entities.MovieEntity;
import edu.tongji.nep4jbackend.repository.GenresRepository;
import edu.tongji.nep4jbackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenresController {

    @Autowired
    GenresRepository genresRepository;

    @GetMapping("/all")
    public Iterable<String> findAll() {
        return genresRepository.getAllGenres();
    }

    @GetMapping("/getGMCount")
    public Integer getAllMoviesCount(HttpServletRequest httpServletRequest)
    {
        String name=httpServletRequest.getParameter("name");
        return  genresRepository.queryGMCount(name);
    }
    @GetMapping("/getGMTitle")
    public List<String> getAllMovies(HttpServletRequest httpServletRequest)
    {
        String name=httpServletRequest.getParameter("name");
        return  genresRepository.queryGMTitle(name);
    }

    @GetMapping("/getNumTitle")
    public List<GenresEntity> getNumTitle(HttpServletRequest httpServletRequest)
    {
        String name=httpServletRequest.getParameter("name");
        return  genresRepository.queryNumTitle(name);
    }
}