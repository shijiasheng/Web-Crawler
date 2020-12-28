package edu.tongji.nep4jbackend.repository;

import edu.tongji.nep4jbackend.entities.DirectorEntity;
import edu.tongji.nep4jbackend.entities.GenresEntity;
import edu.tongji.nep4jbackend.entities.GenresTest;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenresRepository extends Neo4jRepository<DirectorEntity, Long> {

    @Query("MATCH (g:Genres) RETURN g.name")
    public List<String>getAllGenres();

    @Query("MATCH (g:Genres{name:$name})<-[:isgenres]-(movies) RETURN count(movies)")
    public Integer queryGMCount(@Param("name") String name);

    @Query("MATCH (g:Genres{name:$name})<-[:isgenres]-(movies) RETURN movies.title")
    public List<String> queryGMTitle(@Param("name") String name);

    @Query("MATCH (g:Genres{name:$name})<-[:isgenres]-(movies) RETURN count(movies) as num,movies.title as title")
    public List<GenresEntity> queryNumTitle(@Param("name") String name);
}
