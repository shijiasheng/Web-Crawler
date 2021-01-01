package backend.service.hive;

import backend.dao.hive.HiveMovieMapper;
import backend.pojo.hive.HiveMovie;
import backend.pojo.hive.HiveReview;
import backend.pojo.mysql.MysqlMovie;
import backend.pojo.neo4j.Movie;
import common.GetDTO.Command;
import common.GetDTO.GetMoviesDTO;
import common.ReturnDTO.ReturnDTO;
import common.ReturnDTO.ReturnMovieDTO;
import common.ReturnDTO.ReturnProductDTO;
import common.ReturnDTO.ReturnReviewsDTO;
import common.TimeHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class HiveMovieService {
    HiveMovieMapper hiveMovieMapper = new HiveMovieMapper();

    public ReturnDTO getMoviesByHive(GetMoviesDTO getMoviesDTO, JdbcTemplate jdbcTemplate){
        TimeHelper timeHelper=new TimeHelper();
        timeHelper.start();
        long queryTime = timeHelper.getTime();
        List<Command> commands = getMoviesDTO.getCommands();
        List<HiveMovie> hiveMovies = hiveMovieMapper.getMoviesByHive(commands,jdbcTemplate);

        Map<String, ReturnMovieDTO> wrappedMovies = new HashMap<>();

        if (hiveMovies.isEmpty()) {
            return null;
        } else {
            for (HiveMovie m : hiveMovies) {
                if (wrappedMovies.containsKey(m.getTitle())) {
                    ReturnMovieDTO movieDTO = wrappedMovies.get(m.getTitle());
                    movieDTO.getActor().add(m.getActor());
                    movieDTO.getDirector().add(m.getDirector());
                    movieDTO.getGenre().add(m.getGenres());
                    if (movieDTO.getProduct().containsKey(m.getProductId())) {
                        ReturnProductDTO productDTO= movieDTO.getProduct().get(m.getProductId());
                        productDTO.getLanguage().add(m.getLanguage());
                    } else {
                        ReturnProductDTO newProduct = new ReturnProductDTO();
                        newProduct.setProductId(m.getProductId());
                        newProduct.setLanguage(new HashSet<>());
                        newProduct.getLanguage().add(m.getLanguage());
                        newProduct.setBinding(m.getGenres());
                        newProduct.setRuntime(m.getRuntime());
                        newProduct.setRelease_year(m.getRelease_year());
                        newProduct.setRelease_month(m.getRelease_month());
                        newProduct.setRelease_day(m.getRelease_day());
                        newProduct.setRelease_weekday(m.getRelease_weekday());
                        movieDTO.getProduct().put(m.getProductId(), newProduct);
                    }
                } else {
                    ReturnMovieDTO newMovie = new ReturnMovieDTO();
                    newMovie.setActor(new HashSet<>());
                    newMovie.getActor().add(m.getActor());
                    newMovie.setDirector(new HashSet<>());
                    newMovie.getDirector().add(m.getDirector());
                    newMovie.setGenre(new HashSet<>());
                    newMovie.getGenre().add(m.getGenres());
                    newMovie.setProduct(new HashMap<>());
                    newMovie.setTitle(m.getTitle());

                    ReturnProductDTO newProduct = new ReturnProductDTO();
                    newProduct.setProductId(m.getProductId());
                    newProduct.setLanguage(new HashSet<>());
                    newProduct.getLanguage().add(m.getLanguage());
                    newProduct.setBinding(m.getGenres());
                    newProduct.setRuntime(m.getRuntime());
                    newProduct.setRelease_year(m.getRelease_year());
                    newProduct.setRelease_month(m.getRelease_month());
                    newProduct.setRelease_day(m.getRelease_day());
                    newProduct.setRelease_weekday(m.getRelease_weekday());
                    newMovie.getProduct().put(m.getProductId(), newProduct);
                    wrappedMovies.put(m.getTitle(), newMovie);
                }
            }
        }
        List<ReturnMovieDTO> r = new LinkedList<>();
        for (String key: wrappedMovies.keySet()) {
            ReturnMovieDTO movieDTO = wrappedMovies.get(key);
            movieDTO.setVersion(movieDTO.getProduct().size());
            r.add(movieDTO);
        }
        long totalTime = timeHelper.getTime();
        return new ReturnDTO(totalTime, queryTime, r.size(), 0, r.size(), r);

    }

    public ReturnReviewsDTO getReviewByProdutID(String productID, JdbcTemplate jdbcTemplate){
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        List<HiveReview> hiveReviews = hiveMovieMapper.getReviewByProductID(productID,jdbcTemplate);
        long queryTime = timeHelper.getTime();
        return new ReturnReviewsDTO(hiveReviews,queryTime);
    }
}
