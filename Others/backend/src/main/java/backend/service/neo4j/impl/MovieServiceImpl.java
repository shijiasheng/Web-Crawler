package backend.service.neo4j.impl;

import backend.dao.neo4j.MovieMapper;
import backend.pojo.neo4j.*;
import backend.service.neo4j.MovieService;
import common.*;
import common.GetDTO.*;
import common.ReturnDTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class MovieServiceImpl implements MovieService
{

    @Autowired
    MovieMapper movieMapper;

    @Override
    public DetailMovieResult getDetailMovie(String product_id)
    {
        System.out.println(product_id);
        DetailMovieResult res = this.movieMapper.getDetailMovie(product_id);
        System.out.println(res);
        return res;
    }

    @Override
    public List<ReturnMovieResult> getMovie(SearchCommand searchCommand)
    {

        System.out.println(searchCommand);
        List<ReturnMovieResult> res = this.movieMapper.getMovie(searchCommand);
        System.out.println(res);
        return res;
    }

    @Override
    public List<ReturnDirectorResult> getDirectorByActor(String actor)
    {
        System.out.println(actor);
        List<ReturnDirectorResult> res = this.movieMapper.getDirectorByActor(actor);
        System.out.println(res);
        return res;
    }

    @Override
    public List<ReturnActorResult> getActorByDirector(String director)
    {
        System.out.println(director);
        List<ReturnActorResult> res = this.movieMapper.getActorByDirector(director);
        System.out.println(res);
        return res;
    }

    @Override
    public List<ReturnActorResult> getActorByActor(String actor)
    {
        System.out.println(actor);
        List<ReturnActorResult> res = this.movieMapper.getActorByActor(actor);
        System.out.println(res);
        return res;
    }

    @Override
    public List<ReturnMovieResult> getMovieByDirector(String director)
    {
        System.out.println(director);
        List<ReturnMovieResult> res = this.movieMapper.getMovieByDirector(director);
        System.out.println(res);
        return res;
    }

    @Override
    public int getMovieCount(SearchCommand searchCommand)
    {
        return this.movieMapper.getMovieCount(searchCommand);
    }


//    @Override
//    public ReturnDTO getMovieByProductId(String id)
//    {
//        System.out.println(id);
//        long start = System.currentTimeMillis();
//        String title = this.movieMapper.getMovieByProductId(id);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
//        System.out.println(title);
//
////        List<Neo4jMovie> movies = this.movieMapper.getMovieByProductId(id);
////        for (Neo4jMovie m :
////                movies)
////        {
////            System.out.println(m);
////        }
////        System.out.println(movies);
//        return new ReturnDTO();
//    }

    @Override
    public ReturnDTO getAllMatch(GetMoviesDTO getMoviesDTO)
    {
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        //Integer a=this.movieMapper.getMatchMovieTitle(getMoviesDTO.getCommands());

        long queryTime = timeHelper.getTime();

//        Map<String, ReturnMovieDTO> wrappedMovies = new HashMap<>();
//
//        if (movies.isEmpty()){
//            return null;
//        } else {
//            for (Movie m : movies) {
//                if(wrappedMovies.containsKey(m.getTitle())){
//                    ReturnMovieDTO movieDTO=wrappedMovies.get(m.getTitle());
//                    movieDTO.getActor().add(m.getActor());
//                    movieDTO.getDirector().add(m.getDirector());
//                    movieDTO.getGenre().add(m.getGenre());
//                    if(movieDTO.getProduct().containsKey(m.getProductId())){
//                        ReturnProductDTO productDTO= movieDTO.getProduct().get(m.getProductId());
//                        productDTO.getLanguage().add(m.getLanguage());
//                        productDTO.getBinding().add(m.getBinding());
//                    }
//                } else {
//                    ReturnMovieDTO newMovie = new ReturnMovieDTO();
//                    newMovie.setActor(new HashSet<>());
//                    newMovie.getActor().add(m.getActor());
//                    newMovie.setDirector(new HashSet<>());
//                    newMovie.getDirector().add(m.getDirector());
//                    newMovie.setGenre(new HashSet<>());
//                    newMovie.getGenre().add(m.getGenre());
//                    newMovie.setProduct(new HashMap<>());
//
//                    ReturnProductDTO newProduct= new ReturnProductDTO();
//                    newProduct.setProductId(m.getProductId());
//                    newProduct.setLanguage(new HashSet<>());
//                    newProduct.getLanguage().add(m.getLanguage());
//                    newProduct.setBinding(new HashSet<>());
//                    newProduct.getBinding().add(m.getBinding());
//                    newProduct.setRuntime(m.getRuntime());
//                    newProduct.setRelease_year(m.getRelease_year());
//                    newProduct.setRelease_month(m.getRelease_month());
//                    newProduct.setRelease_day(m.getRelease_day());
//                    newProduct.setRelease_weekday(m.getRelease_weekday());
//                    newMovie.getProduct().put(m.getProductId(),newProduct);
//                    wrappedMovies.put(m.getTitle(), newMovie);
//                }
//            }
//        }
//
//        List<ReturnMovieDTO> r = new LinkedList<>();
//
//        for (String key: wrappedMovies.keySet()) {
//            r.add(wrappedMovies.get(key));
//        }
//        long totalTime=timeHelper.getTime();
//        return new ReturnDTO(totalTime, queryTime, r.size(), r);
        return null;
    }

    @Override
    public ReturnDTO getAllMatchSimple(GetMoviesDTO getMoviesDTO)
    {
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        int titleCount = 0;
        List<Command> commands = getMoviesDTO.getCommands();
        for (Command c : commands)
        {
            if (c.getCondition() == "eq" && c.getField() == "title")
            {
                titleCount += 1;
            }
        }
        if (titleCount > 1)
        {
            return null;
        }
        List<Command> _commands = new ArrayList<>();
        for (Command c : getMoviesDTO.getCommands())
        {

            if (c.getField().equals("good_comment_rate") || c.getField().equals("good_comment") || c.getField().equals("total_comment"))
            {
            } else
            {
                _commands.add(c);
                System.out.println(c);
            }
        }
        MatchNum matchNum = this.movieMapper.getMatchNum(_commands);
        long queryTime = timeHelper.getTime();
        List<MovieSimple> movies = this.movieMapper.getLimitMovies(getMoviesDTO.getCommands(), 0, 10);
        Map<String, ReturnMovieDTO> wrappedMovies = new HashMap<>();

        if (movies.isEmpty())
        {
            return null;
        } else
        {
            for (MovieSimple m : movies)
            {
                if (wrappedMovies.containsKey(m.getTitle()))
                {
                    ReturnMovieDTO movieDTO = wrappedMovies.get(m.getTitle());
                    movieDTO.getActor().add(m.getActor());
                    movieDTO.getDirector().add(m.getDirector());
                    movieDTO.getGenre().add(m.getGenre());
                } else
                {
                    ReturnMovieDTO newMovie = new ReturnMovieDTO();
                    newMovie.setTitle(m.getTitle());
                    newMovie.setActor(new HashSet<>());
                    newMovie.getActor().add(m.getActor());
                    newMovie.setDirector(new HashSet<>());
                    newMovie.getDirector().add(m.getDirector());
                    newMovie.setGenre(new HashSet<>());
                    newMovie.getGenre().add(m.getGenre());
                    wrappedMovies.put(m.getTitle(), newMovie);
                }
            }
        }

        List<ReturnMovieDTO> r = new LinkedList<>();

        for (String key : wrappedMovies.keySet())
        {
            r.add(wrappedMovies.get(key));
        }
        long totalTime = timeHelper.getTime();
        return new ReturnDTO(totalTime, queryTime, matchNum.getMovieNum(), matchNum.getProductNum(), r.size(), r);

    }

    //获取指定页的Movies
    @Override
    public ReturnDTO getPageMovies(GetPageMovieDTO getPageMovieDTO)
    {
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        List<Command> commands = getPageMovieDTO.getCommands();
        int page = getPageMovieDTO.getPage();
        List<MovieSimple> limitMovies = movieMapper.getLimitMovies(commands, 10 * (page - 1), 10 * page);
        long queryTime = timeHelper.getTime();
        Map<String, ReturnMovieDTO> wrappedMovies = new HashMap<>();
        if (!limitMovies.isEmpty())
        {
            for (MovieSimple movie : limitMovies)
            {
                if (wrappedMovies.containsKey(movie.getTitle()))
                {
                    wrappedMovies.get(movie.getTitle()).getActor().add(movie.getActor());
                    wrappedMovies.get(movie.getTitle()).getDirector().add(movie.getDirector());
                    wrappedMovies.get(movie.getTitle()).getGenre().add(movie.getGenre());
                } else
                {
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

    //获取product的详细信息，需要指名title
    @Override
    public ReturnDTO getMatchProductSimple(GetProductDTO getProductDTO)
    {
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        if (getProductDTO.getTitle() == null)
        {
            return null;
        }
        List<Command> commands = new ArrayList<>();
        for (Command c : getProductDTO.getCommands())
        {
            System.out.println(c.getField());
            if (c.getField().equals("actor") || c.getField().equals("director") || c.getField().equals("title") || c.getField().equals("genre"))
            {
            } else
            {

                commands.add(c);
                System.out.println(c);
            }
        }

        long queryTime = timeHelper.getTime();

        List<ProductSimple> products = this.movieMapper.getMatchProduct(commands, getProductDTO.getTitle());
        Map<String, ReturnProductDTO> wrappedProducts = new HashMap<>();

        if (products.isEmpty())
        {
            return null;
        } else
        {
            for (ProductSimple m : products)
            {
                if (wrappedProducts.containsKey(m.getProductId()))
                {
                    ReturnProductDTO productDTO = wrappedProducts.get(m.getProductId());
                    productDTO.getLanguage().add(m.getLanguage());
                } else
                {
                    ReturnProductDTO newProduct = new ReturnProductDTO();
                    newProduct.setProductId(m.getProductId());
                    newProduct.setLanguage(new HashSet<>());
                    newProduct.getLanguage().add(m.getLanguage());
                    newProduct.setBinding(m.getBinding());
                    newProduct.setRuntime(m.getRuntime());
                    newProduct.setRelease_year(m.getRelease_year());
                    newProduct.setRelease_month(m.getRelease_month());
                    newProduct.setRelease_day(m.getRelease_day());
                    newProduct.setRelease_weekday(m.getRelease_weekday());
                    newProduct.setRate(m.getRate());
                    newProduct.setGood_comment(m.getGood_comment());
                    newProduct.setTotal_comment(m.getTotal_comment());
                    newProduct.setGood_comment_rate(m.getGood_comment_rate() + "%");
                    wrappedProducts.put(m.getProductId(), newProduct);
                }
            }
        }

        List<ReturnProductDTO> r = new LinkedList<>();

        for (String key : wrappedProducts.keySet())
        {
            r.add(wrappedProducts.get(key));
        }
        long totalTime = timeHelper.getTime();
        return new ReturnDTO(totalTime, queryTime, 1, r.size(), r.size(), r);
    }

    @Override
    public List<Movie> getTenMovie()
    {
        return movieMapper.getTenMovie();
    }

    @Override
    public ReturnDTO getCooperation(GetCooperationDTO getCooperationDTO)
    {
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        // 使用冗余数据优化查询
        //List<ReturnCooperationDTO> r=this.movieMapper.getCooperation(getCooperationDTO);
        List<ReturnCooperationDTO> r = this.movieMapper.getCooperationOpti(getCooperationDTO);
        long queryTime = timeHelper.getTime();
        long totalTime = timeHelper.getTime();
        return new ReturnDTO(totalTime, queryTime, 0, 0, r.size(), r);
    }

    @Override
    public ReturnDTO getTopCooperation(GetTopCooperationDTO getTopCooperationDTO)
    {
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        // 使用冗余数据优化查询
        //List<ReturnCooperationDTO> r = this.movieMapper.getTopCooperation(getTopCooperationDTO);
        List<ReturnCooperationDTO> r = this.movieMapper.getTopCooperationOpti(getTopCooperationDTO);
        long queryTime = timeHelper.getTime();
        long totalTime = timeHelper.getTime();
        return new ReturnDTO(totalTime, queryTime, 0, 0, r.size(), r);
    }


    @Override
    public ReturnDTO getStastic(GetStasticDTO getStasticDTO)
    {
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        List<Stastic> stastic = this.movieMapper.getStastic(getStasticDTO);
        long queryTime = timeHelper.getTime();
        ReturnStasticDTO returnStasticDTO = new ReturnStasticDTO();
        returnStasticDTO.setX(new ArrayList<>());
        returnStasticDTO.setY(new ArrayList<>());
        for (Stastic s : stastic)
        {
            returnStasticDTO.getX().add(s.getX());
            returnStasticDTO.getY().add(s.getY());
        }
        long totalTime = timeHelper.getTime();
        return new ReturnDTO(totalTime, queryTime, 0, 0, 0, returnStasticDTO);
        //return returnStasticDTO;

    }

    @Override
    public ReturnDTO getSery(GetSeryDTO getSeryDTO)
    {
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        List<Sery> sery = this.movieMapper.getSery(getSeryDTO);
        long queryTime = timeHelper.getTime();
        long totalTime = timeHelper.getTime();
        return new ReturnDTO(totalTime, queryTime, 0, 0, sery.size(), sery);
    }

    @Override
    public ReturnDTO getTime(GetTimeDTO getTimeDTO)
    {
        TimeHelper timeHelper = new TimeHelper();
        timeHelper.start();
        List<Stastic> stastic = this.movieMapper.getTime(getTimeDTO);
        long queryTime = timeHelper.getTime();
        ReturnStasticDTO returnStasticDTO = new ReturnStasticDTO();

        returnStasticDTO.setX(new ArrayList<>());
        returnStasticDTO.setY(new ArrayList<>());
        for (Stastic s : stastic)
        {
            returnStasticDTO.getX().add(s.getX());
            returnStasticDTO.getY().add(s.getY());
        }
        returnStasticDTO.setName(String.valueOf(getTimeDTO.getYear()) + "-" + getTimeDTO.getGenre());
        long totalTime = timeHelper.getTime();
        return new ReturnDTO(totalTime, queryTime, 0, 0, 0, returnStasticDTO);
    }



}
