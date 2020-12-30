//package com.tongji.service;
//
//import com.tongji.mapper.*;
//import com.tongji.model.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.LinkedList;
//import java.util.List;
//
//@Service
//public class TestService {
//    @Autowired
//    private MovieMapper movieMapper;
//    @Autowired
//    private ActorMapper actorMapper;
//    @Autowired
//    private DirectorMapper directorMapper;
//    @Autowired
//    private GenreMapper genreMapper;
//    @Autowired
//    private TimeMapper timeMapper;
//
//    @Autowired
//    private MovieDirectorMapper movieDirectorMapper;
//
//    @Autowired
//    private MovieActorMapper movieActorMapper;
//
//
//    @Autowired
//    private MovieGenreMapper movieGenreMapper;
//
//    @Autowired
//    private DirectorActorMapper directorActorMapper;
//
//    public void addMovie() throws ParseException {
//        int index = 0;
//        String pathname = "/root/movieData/data.csv";
//        try (FileReader reader = new FileReader(pathname);
//             BufferedReader br = new BufferedReader(reader)
//        ) {
//            //跳过首字符
//            String line = br.readLine();
//            System.out.println(line);
//            while ((line = br.readLine()) != null) {
//                try {
//                    System.out.println(++index);
//                    String[] lines = line.split(",");
//
//
//                    //对一条电影数据进行读取，并且存放到不同的关系表中
//                    String id = lines[0];
//                    String title = lines[1];
//                    String releaseDate = lines[2];
//                    String week = lines[3];
//                    String genresText = lines[4];
//                    String directorsText = lines[5];
//                    String producers = lines[6];
//                    String actorsText = lines[7];
//                    String supportingActorsText = lines[8];
//                    String ratings = lines[9];
//                    String mediaFormat = lines[10];
//                    String runRime = lines[11];
//                    String MPAARating = lines[12];
//                    String subtitles = lines[13];
//                    String studio = lines[14];
//                    String itemModelNumber = lines[15];
//                    String dateFirstAvailable = lines[16];
//                    String IMDB = lines[17];
//                    String audioLanguages = lines[18];
//                    String linkId = lines[19];
//                    String linkTitle = lines[20];
//
//                    //一些数组
//                    String[] directors = directorsText.split("\\$\\$");
//                    String[] actors = actorsText.split("\\$\\$");
//                    String[] supportingActors = supportingActorsText.split("\\$\\$");
//                    String[] genres = genresText.split("\\$\\$");
//
//                    /*插入时间表中*/
//                    int year = -1, month = -1, day = -1;
//                    int timeId = 0;
//                    if (!releaseDate.equals("##")) {
//                        String[] split = releaseDate.split("/");
//                        if (split.length > 0) {
//                            year = Integer.parseInt(split[0]);
//                        }
//                        if (split.length > 1) {
//                            month = Integer.parseInt(split[1]);
//                        }
//                        if (split.length > 2) {
//                            day = Integer.parseInt(split[2]);
//                        }
//                    }
//
//                    //先检查表中是否有数据
//                    TimeExample timeExample = new TimeExample();
//                    timeExample.createCriteria().andYearEqualTo(year).andMonthEqualTo(month).andDayEqualTo(day);
//                    List<Time> timesList = timeMapper.selectByExample(timeExample);
//                    if (timesList != null && timesList.size() != 0) {
//                        //说明有该数据
//                        Time timeUpdate = timeMapper.selectByPrimaryKey(timesList.get(0).getTimeId());
//
//                        //在后面增加
//                        timeUpdate.setMovie(timeUpdate.getMovie() + "$$" + title);
//                        timeMapper.updateByPrimaryKeySelective(timeUpdate);
//                        timeId = timeUpdate.getTimeId();
//                    }
//                    else {
//                        Time time = new Time();
//                        time.setWeek(Integer.parseInt(week));
//                        time.setYear(year);
//                        time.setMonth(month);
//                        time.setDay(day);
//                        time.setMovie(title);
//                        timeMapper.insertSelective(time);
//                        timeId = time.getTimeId();
//                    }
//
//
//                    /*插入电影表中*/
//                    Movie movie = new Movie();
//                    movie.setProductId(id);
//                    movie.setTimeId(timeId);
//                    movie.setTitle(title);
//                    movie.setGenres(genresText);
//                    movie.setDirector(directorsText);
//                    movie.setSupportingActors(supportingActorsText);
//                    movie.setActor(actorsText);
//                    movie.setRunTime(runRime);
//                    movie.setReleaseDate(releaseDate);
//                    movie.setDateFirstAvailable(dateFirstAvailable);
//                    movie.setStar(Double.parseDouble(ratings));
//                    movie.setLinkId(linkId);
//                    movie.setLinkTitle(linkTitle);
//                /*
//                如果报错,可能是重新生成了generator
//                在orderMapper.insert(order)的mapper里面改成如下:
//                <insert id="insert" parameterType="com.tongji.boying.model.UserOrder" keyProperty="orderId"
//                  keyColumn="order_id" useGeneratedKeys="true">
//                */
//
//                    //插入电影表后需要得到电影id
//                    movieMapper.insertSelective(movie);
//                    int movieId = movie.getMovieId();
//
//                    /*插入题材表中*/
//                    for (String genre : genres) {
//                        //先检查表中是否有数据
//                        GenreExample example = new GenreExample();
//                        example.createCriteria().andNameEqualTo(genre);
//                        List<Genre> genreList = genreMapper.selectByExample(example);
//                        int genreId = 0;
//
//                        if (genreList != null && genreList.size() != 0) {
//                            //说明有该数据
//                            Genre geneUpdate = genreMapper.selectByPrimaryKey(genreList.get(0).getGenreId());
//                            //在后面增加
//                            geneUpdate.setMovies(geneUpdate.getMovies() + "$$" + title);
//                            genreMapper.updateByPrimaryKeySelective(geneUpdate);
//                            genreId = geneUpdate.getGenreId();
//                        }
//                        else {
//                            Genre genreInsert = new Genre();
//                            genreInsert.setName(genre);
//                            genreInsert.setMovies(title);
//                            genreMapper.insertSelective(genreInsert);
//                            genreId = genreInsert.getGenreId();
//                        }
//
//                        /*电影题材表*/
//                        MovieGenre movieGenre = new MovieGenre();
//                        movieGenre.setGenreId(genreId);
//                        movieGenre.setMovieId(movieId);
//                        try {
//                            movieGenreMapper.insertSelective(movieGenre);
//                        }
//                        catch (Exception e) {
//                        }
//
//                    }
//
//
//                    /*插入导演表中*/
//                    List<Integer> directorIds = new LinkedList<>();
//                    for (String director : directors) {
//                        DirectorExample example = new DirectorExample();
//                        example.createCriteria().andNameEqualTo(director);
//                        List<Director> directorsList = directorMapper.selectByExample(example);
//
//                        int directorId = 0;
//
//                        if (directorsList != null && directorsList.size() != 0) {
//                            //说明有该数据
//                            Director directorUpdate = directorMapper.selectByPrimaryKey(directorsList.get(0).getDirectorId());
//                            directorUpdate.setFilming(directorUpdate.getFilming() + "$$" + title);
//                            directorMapper.updateByPrimaryKeySelective(directorUpdate);
//                            directorId = directorUpdate.getDirectorId();
//                        }
//                        else {
//                            Director directorInsert = new Director();
//                            directorInsert.setName(director);
//                            directorInsert.setFilming(title);
//                            directorMapper.insertSelective(directorInsert);
//                            directorId = directorInsert.getDirectorId();
//                        }
//                        /*导演-电影关系*/
//                        MovieDirector movieDirector = new MovieDirector();
//                        movieDirector.setDirectorId(directorId);
//                        movieDirector.setMovieId(movieId);
//                        try {
//                            movieDirectorMapper.insertSelective(movieDirector);
//                        }
//                        catch (Exception e) {
//                        }
//                        directorIds.add(directorId);
//                    }
//
//                    /* 插入演员表中*/
//                    //主演，参演都在
//                    List<Integer> actorIds = new LinkedList<>();
//
//                    //处理参演
//                    for (String actor : actors) {
//                        //参演
//                        ActorExample example = new ActorExample();
//                        example.createCriteria().andNameEqualTo(actor);
//                        List<Actor> actorList = actorMapper.selectByExample(example);
//
//                        int actorId = 0;
//
//                        if (actorList != null && actorList.size() != 0) {
//                            //说明有该数据
//                            Actor actorUpdate = actorMapper.selectByPrimaryKey(actorList.get(0).getActorId());
//                            actorUpdate.setParticipate(actorUpdate.getParticipate() + "$$" + title);
//                            actorMapper.updateByPrimaryKeySelective(actorUpdate);
//                            actorId = actorUpdate.getActorId();
//                        }
//                        else {
//                            Actor actorInsert = new Actor();
//                            actorInsert.setName(actor);
//                            actorInsert.setParticipate(title);
//                            actorMapper.insertSelective(actorInsert);
//                            actorId = actorInsert.getActorId();
//                        }
//                        /*演员-电影关系*/
//                        MovieActor movieActor = new MovieActor();
//                        movieActor.setActorId(actorId);
//                        movieActor.setMovieId(movieId);
//                        movieActor.setIsSupporting(false);
//                        //有可能反复插入
//                        try {
//                            movieActorMapper.insertSelective(movieActor);
//                        }
//                        catch (Exception e) {
//
//                        }
//                        actorIds.add(actorId);
//                    }
//
//                    //处理主演
//                    for (String supportingActor : supportingActors) {
//                        //主演
//                        ActorExample example = new ActorExample();
//                        example.createCriteria().andNameEqualTo(supportingActor);
//                        List<Actor> actorList = actorMapper.selectByExample(example);
//
//                        int actorId = 0;
//
//                        if (actorList != null && actorList.size() != 0) {
//                            //说明有该数据
//                            Actor actorUpdate = actorMapper.selectByPrimaryKey(actorList.get(0).getActorId());
//                            actorUpdate.setStarring(actorUpdate.getStarring() + "$$" + title);
//                            actorMapper.updateByPrimaryKeySelective(actorUpdate);
//                            actorId = actorUpdate.getActorId();
//                        }
//                        else {
//                            Actor actorInsert = new Actor();
//                            actorInsert.setName(supportingActor);
//                            actorInsert.setStarring(title);
//                            actorMapper.insertSelective(actorInsert);
//                            actorId = actorInsert.getActorId();
//                        }
//                        /*演员-电影关系*/
//                        MovieActor movieActor = new MovieActor();
//                        movieActor.setActorId(actorId);
//                        movieActor.setMovieId(movieId);
//                        movieActor.setIsSupporting(true);
//                        //有可能反复插入
//                        try {
//                            movieActorMapper.insertSelective(movieActor);
//                        }
//                        catch (Exception e) {
//
//                        }
//                        actorIds.add(actorId);
//                    }
//
//                    /*导演和演员的关系表*/
//                    for (Integer directorId : directorIds) {
//                        for (Integer actorId : actorIds) {
//                            //先查询
//                            DirectorActorExample directorActorExample = new DirectorActorExample();
//                            directorActorExample.createCriteria().andActorIdEqualTo(actorId).andDirectorIdEqualTo(directorId);
//                            List<DirectorActor> directorActors = directorActorMapper.selectByExample(directorActorExample);
//                            if (directorActors != null && directorActors.size() != 0) {
//                                DirectorActor directorActor = directorActorMapper.selectByPrimaryKey(directorActors.get(0).getDirectorId(), directorActors.get(0).getActorId());
//                                directorActor.setCount(directorActor.getCount() + 1);
//                                directorActorMapper.updateByPrimaryKeySelective(directorActor);
//
//                            }
//                            else {
//                                DirectorActor directorActor = new DirectorActor();
//                                directorActor.setDirectorId(directorId);
//                                directorActor.setActorId(actorId);
//                                directorActor.setCount(1);
//                                directorActorMapper.insertSelective(directorActor);
//                            }
//                        }
//                    }
//                }
//                catch (Exception e) {
//
//                }
//            }
//
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
