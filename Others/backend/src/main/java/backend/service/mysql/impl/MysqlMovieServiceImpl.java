package backend.service.mysql.impl;

import backend.dao.mysql.MysqlMovieMapper;
import backend.pojo.mysql.LimitMovie;
import backend.pojo.mysql.MatchCount;
import backend.service.mysql.MysqlMovieService;
import common.GetDTO.Command;
import common.GetDTO.GetMoviesDTO;
import common.GetDTO.GetPageMovieDTO;
import common.ReturnDTO.*;
import common.TimeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MysqlMovieServiceImpl implements MysqlMovieService {

    @Autowired
    MysqlMovieMapper mysqlMovieMapper;

    @Override
    public ReturnDTO getAllMatch(GetMoviesDTO getMoviesDTO) {
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        List<Command> commands = getMoviesDTO.getCommands();
//        List<MysqlMovie> mysqlMovies = mysqlMovieMapper.getMovies(commands);
        MatchCount matchCount = mysqlMovieMapper.getMovies(commands);
        long queryTime = timeHelper.getTime();

//        Map<String, ReturnMovieDTO> wrappedMovies = new HashMap<>();
//        if (mysqlMovies.isEmpty()) {
//            return null;
//        } else {
//            for (MysqlMovie m : mysqlMovies) {
//                if (wrappedMovies.containsKey(m.getTitle())) {
//                    ReturnMovieDTO movieDTO = wrappedMovies.get(m.getTitle());
//                    movieDTO.getActor().add(m.getActor());
//                    movieDTO.getDirector().add(m.getDirector());
//                    movieDTO.getGenre().add(m.getGenres());
//                    if (movieDTO.getProduct().containsKey(m.getProductId())) {
//                        ReturnProductDTO productDTO= movieDTO.getProduct().get(m.getProductId());
//                        productDTO.getLanguage().add(m.getLanguage());
//                    } else {
//                        ReturnProductDTO newProduct = new ReturnProductDTO();
//                        newProduct.setProductId(m.getProductId());
//                        newProduct.setLanguage(new HashSet<>());
//                        newProduct.getLanguage().add(m.getLanguage());
//                        newProduct.setBinding(m.getBinding());
//                        newProduct.setRuntime(m.getRuntime());
//                        newProduct.setRelease_year(m.getRelease_year());
//                        newProduct.setRelease_month(m.getRelease_month());
//                        newProduct.setRelease_day(m.getRelease_day());
//                        newProduct.setRelease_weekday(m.getRelease_weekday());
//                        movieDTO.getProduct().put(m.getProductId(), newProduct);
//                    }
//                } else {
//                    ReturnMovieDTO newMovie = new ReturnMovieDTO();
//                    newMovie.setActor(new HashSet<>());
//                    newMovie.getActor().add(m.getActor());
//                    newMovie.setDirector(new HashSet<>());
//                    newMovie.getDirector().add(m.getDirector());
//                    newMovie.setGenre(new HashSet<>());
//                    newMovie.getGenre().add(m.getGenres());
//                    newMovie.setProduct(new HashMap<>());
//                    newMovie.setTitle(m.getTitle());
//
//                    ReturnProductDTO newProduct = new ReturnProductDTO();
//                    newProduct.setProductId(m.getProductId());
//                    newProduct.setLanguage(new HashSet<>());
//                    newProduct.getLanguage().add(m.getLanguage());
//                    newProduct.setBinding(m.getBinding());
//                    newProduct.setRuntime(m.getRuntime());
//                    newProduct.setRelease_year(m.getRelease_year());
//                    newProduct.setRelease_month(m.getRelease_month());
//                    newProduct.setRelease_day(m.getRelease_day());
//                    newProduct.setRelease_weekday(m.getRelease_weekday());
//                    newMovie.getProduct().put(m.getProductId(), newProduct);
//                    wrappedMovies.put(m.getTitle(), newMovie);
//                }
//            }
//        }
//        List<ReturnMovieDTO> r = new LinkedList<>();
//        for (String key: wrappedMovies.keySet()) {
//            ReturnMovieDTO movieDTO = wrappedMovies.get(key);
//            movieDTO.setVersion(movieDTO.getProduct().size());
//            r.add(movieDTO);
//        }
        long totalTime = timeHelper.getTime();
        return new ReturnDTO(totalTime, queryTime, matchCount.getMovieCount(), matchCount.getProductCount(), 1, matchCount);
    }

    @Override
    public ReturnDTO getMovies(GetMoviesDTO getMoviesDTO) {
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        List<Command> commands = getMoviesDTO.getCommands();
        MatchCount matchCount = null;
        if (commands.size() == 1 && commands.get(0).getField().equals("language")) {
            matchCount = mysqlMovieMapper.getMatchCountByLanguage(commands.get(0).getValue());
        } else {
            matchCount = mysqlMovieMapper.getMatchCount(commands);
        }
        long queryTime = timeHelper.getTime();
        List<LimitMovie> limitMovies = null;
        if (commands.size() == 1 && commands.get(0).getField().equals("language")) {
            limitMovies = mysqlMovieMapper.getLimitMoviesByLanguage(commands.get(0).getValue(), 0, 10);
        } else {
            limitMovies = mysqlMovieMapper.getLimitMovies(commands, 0, 10);
        }
        Map<String, ReturnMovieDTO> wrappedMovies = new HashMap<>();
        if (!limitMovies.isEmpty()) {
            for (LimitMovie movie : limitMovies) {
                if (wrappedMovies.containsKey(movie.getTitle())) {
                    wrappedMovies.get(movie.getTitle()).getActor().add(movie.getActor());
                    wrappedMovies.get(movie.getTitle()).getDirector().add(movie.getDirector());
                    wrappedMovies.get(movie.getTitle()).getGenre().add(movie.getGenre());
                } else {
                    ReturnMovieDTO newMovie = new ReturnMovieDTO();
                    newMovie.setTitle(movie.getTitle());
                    newMovie.setActor(new HashSet<>());
                    newMovie.getActor().add(movie.getActor());
                    newMovie.setDirector(new HashSet<>());
                    newMovie.getDirector().add(movie.getDirector());
                    newMovie.setGenre(new HashSet<>());
                    newMovie.getGenre().add(movie.getGenre());
                    wrappedMovies.put(movie.getTitle(), newMovie);
                }
            }
        }
        long totalTime = timeHelper.getTime();
        return new ReturnDTO(totalTime, queryTime, matchCount.getMovieCount(), matchCount.getProductCount(), wrappedMovies.size(), wrappedMovies.values());
    }

    @Override
    public ReturnDTO getMoviesByArr(GetMoviesDTO getMoviesDTO) {
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        List<Command> commands = getMoviesDTO.getCommands();
        MatchCount matchCount = mysqlMovieMapper.getMatchCountByArr(commands);
        long queryTime = timeHelper.getTime();
        List<LimitMovie> limitMovies = mysqlMovieMapper.getLimitMoviesByArr(commands, 0, 10);
        Map<String, ReturnMovieDTO> wrappedMovies = new HashMap<>();
        if (!limitMovies.isEmpty()) {
            for (LimitMovie movie : limitMovies) {
                if (wrappedMovies.containsKey(movie.getTitle())) {
                    wrappedMovies.get(movie.getTitle()).getActor().add(movie.getActor());
                    wrappedMovies.get(movie.getTitle()).getDirector().add(movie.getDirector());
                    wrappedMovies.get(movie.getTitle()).getGenre().add(movie.getGenre());
                } else {
                    ReturnMovieDTO newMovie = new ReturnMovieDTO();
                    newMovie.setTitle(movie.getTitle());
                    newMovie.setActor(new HashSet<>());
                    newMovie.getActor().add(movie.getActor());
                    newMovie.setDirector(new HashSet<>());
                    newMovie.getDirector().add(movie.getDirector());
                    newMovie.setGenre(new HashSet<>());
                    newMovie.getGenre().add(movie.getGenre());
                    wrappedMovies.put(movie.getTitle(), newMovie);
                }
            }
        }
        long totalTime = timeHelper.getTime();
        return new ReturnDTO(totalTime, queryTime, matchCount.getMovieCount(), matchCount.getProductCount(), wrappedMovies.size(), wrappedMovies.values());
    }

    @Override
    public ReturnDTO getPageMovies(GetPageMovieDTO getPageMovieDTO) {
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        List<Command> commands = getPageMovieDTO.getCommands();
        int page = getPageMovieDTO.getPage();
        List<LimitMovie> limitMovies = mysqlMovieMapper.getLimitMovies(commands, 10*(page-1), 10*page);
        long queryTime = timeHelper.getTime();
        Map<String, ReturnMovieDTO> wrappedMovies = new HashMap<>();
        if (!limitMovies.isEmpty()) {
            for (LimitMovie movie : limitMovies) {
                if (wrappedMovies.containsKey(movie.getTitle())) {
                    wrappedMovies.get(movie.getTitle()).getActor().add(movie.getActor());
                    wrappedMovies.get(movie.getTitle()).getDirector().add(movie.getDirector());
                    wrappedMovies.get(movie.getTitle()).getGenre().add(movie.getGenre());
                } else {
                    ReturnMovieDTO newMovie = new ReturnMovieDTO();
                    newMovie.setTitle(movie.getTitle());
                    newMovie.setActor(new HashSet<>());
                    newMovie.getActor().add(movie.getActor());
                    newMovie.setDirector(new HashSet<>());
                    newMovie.getDirector().add(movie.getDirector());
                    newMovie.setGenre(new HashSet<>());
                    newMovie.getGenre().add(movie.getGenre());
                    wrappedMovies.put(movie.getTitle(), newMovie);
                }
            }
        }
        long totalTime = timeHelper.getTime();
        return new ReturnDTO(totalTime, queryTime, 0, 0, wrappedMovies.size(), wrappedMovies.values());
    }

    @Override
    public List<String> getGenres() {
        return mysqlMovieMapper.getGenres();
    }

    @Override
    public List<String> getLanguages() {
        return mysqlMovieMapper.getLanguages();
    }

    @Override
    public List<String> getBindings() {
        return mysqlMovieMapper.getBindings();
    }

}
