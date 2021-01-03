package backend.dao.neo4j;

import backend.pojo.neo4j.*;
import common.*;
import common.GetDTO.*;
import common.ReturnDTO.ReturnCooperationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.tools.ant.taskdefs.Get;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MovieMapper
{

    DetailMovieResult getDetailMovie(String product_id);

    int getMovieCount(@Param("searchCommand") SearchCommand searchCommand);

    List<ReturnMovieResult> getMovie(@Param("searchCommand") SearchCommand searchCommand);

    List<ReturnDirectorResult> getDirectorByActor(@Param("actor") String actor, @Param("skip") int skip, @Param("limit") int limit);

    List<ReturnActorResult> getActorByDirector(@Param("director") String director, @Param("skip") int skip, @Param("limit") int limit);

    List<ReturnActorResult> getActorByActor(@Param("actor") String actor, @Param("skip") int skip, @Param("limit") int limit);

    List<ReturnMovieResult> getMovieByDirector(String director);

    List<Movie> getAllMovieNeo4j(@Param("commands") List<Command> commands);

    List<MovieSimple> getLimitMovies(@Param("commands") List<Command> commands,
                                     @Param("start") int start, @Param("end") int end);

    List<ProductSimple> getMatchProduct(@Param("commands") List<Command> commands, @Param("title") String title);

    MatchNum getMatchNum(@Param("commands") List<Command> commands);

    List<Movie> getTenMovie();

    List<ReturnCooperationDTO> getCooperation(@Param("getCooperationDTO") GetCooperationDTO getCooperationDTO);

    List<ReturnCooperationDTO> getCooperationOpti(@Param("getCooperationDTO") GetCooperationDTO getCooperationDTO);

    List<ReturnCooperationDTO> getTopCooperation(@Param("getTopCooperationDTO") GetTopCooperationDTO getTopCooperationDTO);

    List<ReturnCooperationDTO> getTopCooperationOpti(@Param("getTopCooperationDTO") GetTopCooperationDTO getTopCooperationDTO);


    List<Stastic> getStastic(@Param("getStasticDTO") GetStasticDTO getStasticDTO);

    List<Sery> getSery(@Param("getSeryDTO") GetSeryDTO getSeryDTO);

    List<Stastic> getTime(@Param("getTimeDTO") GetTimeDTO getTimeDTO);

    int getDirectorByActorCount(String actor);

    int getActorByDirectorCount(String director);

    int getActorByActorCount(String actor);

    List<ReturnReviewResult> getReview(@Param("productId") String productId);

    List<ReturnReviewResult> getSeriesReview(@Param("productId") String productId);
}
