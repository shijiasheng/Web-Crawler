package com.tongji;

import com.tongji.mapper.*;
import com.tongji.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class)
public class MainTest
{
    @Autowired
    ProductdataMapper mapper;
    @Autowired
    MovieMapper movieMapper;
    @Autowired
    ActorMapper actorMapper;
    @Autowired
    DirectorMapper directorMapper;
    @Autowired
    GenreMapper genreMapper;
    @Autowired
    TimeMapper timeMapper;

    @Test
    public void test3() throws ParseException
    {
        String dateStr = "December 11, 2012";
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        Date date = format.parse(dateStr);
        format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(date));
    }

    @Test
    public void test() throws ParseException
    {
        //获取所有记录
        ProductdataExample productdataExample = new ProductdataExample();
        List<Productdata> productdata = mapper.selectByExample(productdataExample);

        int count = 0;
        for (Productdata item : productdata)
        {
            //打印处理行数
            ++count;
            System.out.println(count);

            //对原始表每一字段进行清洗处理,如从字符串中提取一个字符串数组
            String productId = item.getId();
            String title = item.getTitle();
            String genresRaw = item.getGenres();//字符串内可能包含很多种类型,需要处理
            String genres = changeGenres(genresRaw);//获取该电影的类型列表
            String directorsRaw = item.getDirector();//同理,可能有多个导演
            String[] directors = changeDirectors(directorsRaw);
            String supportingActorsRaw = item.getSupportingActors();//同理,处理主演
            String[] supportingActors = changeSupportingActors(supportingActorsRaw);
            String actorsRaw = item.getActor();//同理,处理演员
            String[] actors = changeActors(actorsRaw);
            int runTime = changeTime(item.getRunTime());//获取以秒为单位的运行时间
            //因为时间可能是null 或者是 December 11, 2012 或者是  2012 格式
            //所以要预先处理
            String dateString = item.getReleaseDate();
            Date releaseDate = null;
            boolean onlyYear = false;
            if (dateString == null)
            {

            }
            else if (dateString.split(" ").length == 1) //说明是类似2012这种只有年份
            {
                onlyYear = true;
            }
            else
            {
                releaseDate = changeReleaseDateRaw(dateString);//获取发布日期
            }

            Date dateFirstAvailable = changeDateFirstAvailableRaw(item.getDateFirstAvailable());//获取dateFirstAvailable
            String series = "test";//这个不用处理,因为本身就是自己按照规定格式添加的,且不添加额外的系列表支持


            //对movie表处理
            Movie movie = new Movie();
            movie.setId(productId);
            movie.setTitle(title);
            StringBuilder stringBuilder = new StringBuilder();
            //处理类型
            if (genres != null)
            {
                stringBuilder.append(genres);
                stringBuilder.append("&");
            }
            movie.setGenres(stringBuilder.toString());
            //处理导演
            stringBuilder = new StringBuilder();
            if (directors != null)
            {
                for (String s : directors)
                {
                    stringBuilder.append(s);
                    stringBuilder.append("&");
                }
            }
            movie.setDirector(stringBuilder.toString());
            //处理主演
            stringBuilder = new StringBuilder();
            if (supportingActors != null)
            {
                for (String s : supportingActors)
                {
                    stringBuilder.append(s);
                    stringBuilder.append("&");
                }
            }
            movie.setSupportingActors(stringBuilder.toString());
            //处理演员
            stringBuilder = new StringBuilder();
            if (actors != null)
            {
                for (String s : actors)
                {
                    stringBuilder.append(s);
                    stringBuilder.append("&");
                }
            }
            movie.setActor(stringBuilder.toString());
            //处理时间
            movie.setRunTime(runTime);
            movie.setReleaseDate(releaseDate);
            movie.setDateFirstAvailable(dateFirstAvailable);
            movie.setSeries(series);
            //将该记录插入movie表中
            movieMapper.insertSelective(movie);

            //对director表进行处理
            if (directors != null)
            {
                for (String s : directors)
                {
                    //先获取导演
                    Director director = directorMapper.selectByPrimaryKey(s);
                    //获取他导演的片
                    stringBuilder = new StringBuilder();
                    String filming = director.getFilming();
                    //如果这个导演导演过电影,就加入原先的电影
                    if (filming != null && !filming.isEmpty()) stringBuilder.append(filming);
                    //如果这部电影有名字
                    if (title != null && !title.isEmpty()) stringBuilder.append(title);
                    director.setFilming(stringBuilder.toString());
                    directorMapper.updateByPrimaryKeySelective(director);
                }
            }

            //针对主演,更新actor表
            if (supportingActors != null)
            {
                for (String s : supportingActors)
                {
                    Actor actor = actorMapper.selectByPrimaryKey(s);
                    stringBuilder = new StringBuilder();
                    String starring = actor.getStarring();
                    if (starring != null && !starring.isEmpty()) stringBuilder.append(starring);
                    //如果这部电影有名字
                    if (title != null && !title.isEmpty()) stringBuilder.append(title);
                    actor.setStarring(stringBuilder.toString());
                    actorMapper.updateByPrimaryKeySelective(actor);
                }
            }


            //针对参演人员,更新actor表
            if (actors != null)
            {
                for (String s : actors)
                {
                    Actor actor = actorMapper.selectByPrimaryKey(s);
                    stringBuilder = new StringBuilder();
                    String participate = actor.getParticipate();
                    if (participate != null && !participate.isEmpty()) stringBuilder.append(participate);
                    //如果这部电影有名字
                    if (title != null && !title.isEmpty()) stringBuilder.append(title);
                    actor.setParticipate(stringBuilder.toString());
                    actorMapper.updateByPrimaryKeySelective(actor);
                }
            }


            //更新genre表
            if (genres != null)
            {
                Genre genre = genreMapper.selectByPrimaryKey(genres);
                stringBuilder = new StringBuilder();
                String movies = genre.getMovies();
                if (movies != null && !movies.isEmpty()) stringBuilder.append(movies);
                //如果这部电影有名字
                if (title != null && !title.isEmpty()) stringBuilder.append(title);
                genre.setMovies(stringBuilder.toString());
                genreMapper.updateByPrimaryKeySelective(genre);
            }


            //更新time表
            if (releaseDate != null)
            {
                //不更新
            }
            else if(onlyYear)
            {
                //只更新年
                Time time = new Time();
                int year = Integer.parseInt(dateString);
                time.setYear(year);
                time.setMovie(title);
                timeMapper.insertSelective(time);
            }
            else
            {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(releaseDate);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;//因为从0开始的
                int day = calendar.get(Calendar.DATE);
                String week = simpleDateFormat.format(releaseDate);
//        3—5月为春季，6—8月为夏季，9-11月为秋季，12—2月为冬季
                String season = "";
                if (month == 3 || month == 4 || month == 5)
                {
                    season = "spring";
                }
                else if (month == 6 || month == 7 || month == 8)
                {
                    season = "summer";
                }
                else if (month == 9 || month == 10 || month == 11)
                {
                    season = "autumn";
                }
                else
                {
                    season = "winter";
                }
                Time time = new Time();
                time.setYear(year);
                time.setMonth(month);
                time.setDay(day);
                time.setWeek(week);
                time.setSeason(season);
                time.setMovie(title);

                timeMapper.insertSelective(time);
            }
        }
    }


    /*
    null
    December 11, 2012
     */
    private Date changeDateFirstAvailableRaw(String dateFirstAvailable) throws ParseException
    {
        if(dateFirstAvailable==null) return null;
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        Date date = format.parse(dateFirstAvailable);
        return date;
    }

    /*
    null
    December 11, 2012
    2012
     */
    private Date changeReleaseDateRaw(String releaseDate) throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        Date date = format.parse(releaseDate);
        return date;
    }

    private String[] changeActors(String actorsRaw)
    {
        if(actorsRaw==null) return null;
        String[] split = actorsRaw.split(" & ");
        return split;
    }

    private String[] changeSupportingActors(String supportingActorsRaw)
    {
        if(supportingActorsRaw==null) return null;
        String[] split = supportingActorsRaw.split(" & ");
        return split;
    }

    private String[] changeDirectors(String directorsRaw)
    {
        if(directorsRaw==null) return null;
        String[] split = directorsRaw.split(" & ");
        return split;
    }

    /*
    处理字符串内可能包含很多种类型,返回类型字符串数组
    其中,用" & "才是几种类型的合并
    Pop & Contemporary
    Movies
    All A&E Titles
    All Sony Pictures Titles
    Cooper, Alice
     */
    private String changeGenres(String genres)
    {
        return genres;
    }

    /*
    讲字符串时间处理为以s计算的时间
    2 hours and 1 minute
    1 hour and 1 minute
    1 hour and 10 minutes
    2 hours and 27 minutes
    30 minutes
    1 hour
    1 minute
    45sec
     */
    public int changeTime(String runTime)
    {
        int result = 0;

        if (runTime == null)
        {
            return 0;
        }
        else if ("1 hour".equals(runTime))
        {
            result = 3600;
        }
        else if ("1 minute".equals(runTime))
        {
            result = 60;
        }
        else if (Pattern.matches("^.{1,2}sec$", runTime))
        {
            return Integer.parseInt(runTime.replaceFirst("sec", ""));
        }
        else
        {
            /*
                只剩下以下几种情况:
                2 hours and 1 minute
                1 hour and 1 minute
                1 hour and 10 minutes
                2 hours and 27 minutes
                30 minutes
             */
            int hour = 0;
            String pattern = "^.*hour.* and.*$";
            if (Pattern.matches(pattern, runTime))
            {
                //说明前面有小时
                int ch = runTime.charAt(1) - '0';

                if (ch >= 0 && ch <= 9)
                    hour = Integer.parseInt(runTime.substring(0, 2));
                else
                    hour = Integer.parseInt(runTime.substring(0, 1));
            }

            String[] strings = runTime.split(" ");
            int minute = Integer.parseInt(strings[strings.length - 2]);
            return 3600 * hour + 60 * minute;
        }
//        最后转换时间单位为秒
        return result;
    }

}
