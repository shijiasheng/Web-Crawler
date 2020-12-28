package edu.tongji.nep4jbackend.repository;

import edu.tongji.nep4jbackend.entities.MovieEntity;
import org.neo4j.cypher.internal.compiler.v2_2.functions.Str;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends Neo4jRepository<MovieEntity, String> {

    @Query("match (m:Movie{id:$id}) RETURN m")
    MovieEntity findByid(@Param("id") String id);

    @Query("match (m:Movie) RETURN m.title")
    List<String> getAlltitle();

    @Query("match (m:Movie{id:$id}) RETURN m.title")
    List<String> getTitleById(@Param("id") String id);
}