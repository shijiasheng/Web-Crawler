package backend.service.mysql;

import common.GetDTO.GetMoviesDTO;
import common.GetDTO.GetPageMovieDTO;
import common.GetDTO.GetProductDTO;
import common.ReturnDTO.ReturnDTO;

import java.util.List;

public interface MysqlMovieService {

    ReturnDTO getAllMatch(GetMoviesDTO getMoviesDTO);

    ReturnDTO getMovies(GetMoviesDTO getMoviesDTO);

    ReturnDTO getPageMovies(GetPageMovieDTO getPageMovieDTO);

    ReturnDTO getMoviesByArr(GetMoviesDTO getMoviesDTO);

    List<String> getGenres();

    List<String> getLanguages();

    List<String> getBindings();

}
