package backend.service.neo4j;

import backend.pojo.neo4j.Movie;
import common.DetailMovieResult;
import common.GetDTO.*;
import common.ReturnDTO.ReturnCooperationDTO;
import common.ReturnDTO.ReturnDTO;
import common.ReturnDTO.ReturnStasticDTO;
import common.ReturnDirectorResult;
import common.ReturnMovieResult;
import common.SearchCommand;
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

    List<ReturnDirectorResult> getDirectorByActor(String actor);

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

}
