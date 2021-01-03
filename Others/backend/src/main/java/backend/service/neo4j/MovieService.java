package backend.service.neo4j;

import backend.pojo.neo4j.Movie;
import common.*;
import common.GetDTO.*;
import common.ReturnDTO.ReturnCooperationDTO;
import common.ReturnDTO.ReturnDTO;
import common.ReturnDTO.ReturnStasticDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Licoy
 * @version 2018/4/28/9:56
 */
public interface MovieService
{

    List<ReturnMovieResult> getMovieByDirector(String director);

    int getMovieCount(SearchCommand searchCommand);

    List<ReturnMovieResult> getMovie(SearchCommand searchCommand);

    List<ReturnDirectorResult> getDirectorByActor(String actor, int skip, int limit);

    int getDirectorByActorCount(String actor);

    List<ReturnActorResult> getActorByDirector(String director, int skip, int limit);

    int getActorByDirectorCount(String director);

    List<ReturnActorResult> getActorByActor(String actor,int skip,int limit);

    int getActorByActorCount(String actor);

//    ReturnDTO getMovieByProductId(String id);

    ReturnDTO getAllMatch(GetMoviesDTO getMoviesDTO);

    ReturnDTO getAllMatchSimple(GetMoviesDTO getMoviesDTO);

    ReturnDTO getPageMovies(GetPageMovieDTO getPageMovieDTO);

    ReturnDTO getMatchProductSimple(GetProductDTO getProductDTO);

    List<Movie> getTenMovie();

    ReturnDTO getCooperation(GetCooperationDTO getCooperationDTO);

    ReturnDTO getTopCooperation(GetTopCooperationDTO getTopCooperationDTO);

    ReturnDTO getStastic(GetStasticDTO getStasticDTO);

    ReturnDTO getSery(GetSeryDTO getSeryDTO);

    ReturnDTO getTime(GetTimeDTO getTimeDTO);

    DetailMovieResult getDetailMovie(String product_id);

    List<ReturnReviewResult> getReview(String productId);

    List<ReturnReviewResult> getSeriesReview(String productId);

    Integer getMonthStatistics(String month);

    Integer getWeekStatistics(String toString);
}
