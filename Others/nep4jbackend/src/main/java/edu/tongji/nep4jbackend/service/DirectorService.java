package edu.tongji.nep4jbackend.service;

import edu.tongji.nep4jbackend.entities.MovieEntity;
import edu.tongji.nep4jbackend.repository.DirectorRepository;
import edu.tongji.nep4jbackend.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DirectorService {

    private final static Logger LOG = LoggerFactory.getLogger(DirectorService.class);

    private final DirectorRepository directorRepository;


    public DirectorService(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Transactional(readOnly = true)
    public MovieEntity findById(String id) {
        MovieEntity result = directorRepository.findByid(id);
        return result;
    }
}
