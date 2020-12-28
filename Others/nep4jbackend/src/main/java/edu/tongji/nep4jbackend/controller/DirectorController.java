package edu.tongji.nep4jbackend.controller;

import edu.tongji.nep4jbackend.entities.MovieEntity;
import edu.tongji.nep4jbackend.repository.DirectorRepository;
import edu.tongji.nep4jbackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/direct")
public class DirectorController {

    @Autowired
    DirectorRepository dRepository;


    @GetMapping("/getAllMoviesCount")
    public Integer getAllMoviesCount(HttpServletRequest httpServletRequest)
    {
        String name=httpServletRequest.getParameter("name");
        return  dRepository.queryDirMovieCount(name);
    }
    @GetMapping("/getAllMovies")
    public List<String> getAllMovies(HttpServletRequest httpServletRequest)
    {
        String name=httpServletRequest.getParameter("name");
        return  dRepository.queryDirMovie(name);
    }
}