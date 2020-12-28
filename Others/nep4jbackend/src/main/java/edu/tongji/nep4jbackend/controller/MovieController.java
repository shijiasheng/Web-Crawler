package edu.tongji.nep4jbackend.controller;

import edu.tongji.nep4jbackend.entities.MovieEntity;
import edu.tongji.nep4jbackend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("/all")
    public Iterable<MovieEntity> findAll() {
        return movieRepository.findAll();
    }

    @GetMapping("/findByid")
    public MovieEntity findByid(HttpServletRequest httpServletRequest)
    {
        String id=httpServletRequest.getParameter("id");
        return  movieRepository.findByid(id);
    }

    @GetMapping("/getallname")
    public List<String> findAllName()
    {
        return  movieRepository.getAlltitle();
    }


    @GetMapping("/getTBI")
    public List<String> getTBI(HttpServletRequest httpServletRequest)
    {
        String id=httpServletRequest.getParameter("id");
        return  movieRepository.getTitleById(id);
    }
}