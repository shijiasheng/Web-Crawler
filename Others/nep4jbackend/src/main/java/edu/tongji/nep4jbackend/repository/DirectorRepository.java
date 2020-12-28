package edu.tongji.nep4jbackend.repository;

import edu.tongji.nep4jbackend.entities.DirectorEntity;
import edu.tongji.nep4jbackend.entities.MovieEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DirectorRepository extends Neo4jRepository<DirectorEntity, Long> {

    MovieEntity findByid(String id);

    @Query("MATCH (d:Director{name:$name})-[:Direct]->(movies) RETURN count(movies)")
    public Integer queryDirMovieCount(@Param("name") String name);

    @Query("MATCH (d:Director{name:$name})-[:Direct]->(movies) RETURN movies.title")
    public List<String> queryDirMovie(@Param("name") String name);
}
