package edu.tongji.nep4jbackend.repository;

import edu.tongji.nep4jbackend.entities.ActorEntity;
import edu.tongji.nep4jbackend.entities.DirectorEntity;
import edu.tongji.nep4jbackend.entities.MovieEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface ActorRepository extends Neo4jRepository<DirectorEntity, Long> {

    @Query("match (a:Actor) RETURN a.name")
    ArrayList<String> getAllActor();

    @Query("MATCH (a:Actor {name:$name})-[:ActIn]->(movies) RETURN movies")
    public List<MovieEntity> queryActMovie(@Param("name") String name);

    @Query("MATCH (a1:Actor{name:$name})-[:ActIn]->(movies)<-[:ActIn]-(a2:Actor)  RETURN a2.name")
    public List<String> queryCoActMovie(@Param("name")String name);

    @Query("MATCH (a1:Actor{name:$name1})-[:ActIn]->(movies)<-[:ActIn]-(a2:Actor{name:$name2})  RETURN count(movies)")
    public Integer queryCoActMovieCount(@Param("name1")String name1,@Param("name2")String name2);
}
