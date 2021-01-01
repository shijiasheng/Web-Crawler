package backend.service.neo4j;

import backend.pojo.neo4j.Movie;
import common.GetDTO.*;
import common.ReturnDTO.ReturnCooperationDTO;
import common.ReturnDTO.ReturnDTO;
import common.ReturnDTO.ReturnStasticDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/28/9:56
 */
public interface MovieService
{

    ReturnDTO getMovieByProductId(String id);

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
}
