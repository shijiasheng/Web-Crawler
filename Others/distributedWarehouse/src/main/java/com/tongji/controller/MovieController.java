package com.tongji.controller;

import com.tongji.common.api.CommonResult;
import com.tongji.common.exception.Asserts;
import com.tongji.dto.SearchInfo;
import com.tongji.model.Movie;
import com.tongji.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 数据仓库查询相关Controller
 */
@Controller
@Api(tags = "MovieController", description = "数据仓库所有查询相关API")
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    /*
    分页插件
    title
    director
    actor
    genres
    star

    - 按照时间进行查询及统计（例如XX年有多少电影，XX年XX月有多少电影，XX年XX季度有多少电影，周二新增多少电影等）
    - 按照电影名称进行查询及统计（例如 XX电影共有多少版本等）
	- 按照导演进行查询及统计（例如 XX导演共有多少电影等）
    - 按照演员进行查询及统计（例如 XX演员主演多少电影，XX演员参演多少电影等）
	- 按照演员和导演的关系进行查询及统计（例如经常合作的演员有哪些，经常合作的导演和演员有哪些）
    - 按照电影类别进行查询及统计（例如 Action电影共有多少，Adventure电影共有多少等）
	- 按照用户评价进行查询及统计（例如用户评分3分以上的电影有哪些，用户评价中有正面评价的电影有哪些等）

    - 按照上述条件的组合查询和统计
     */

    @ApiOperation("复杂的条件查询及查询时间统计(查询电影)")
    @RequestMapping(value = "/searchMovie", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, Object>> searchMovie(@RequestBody Map<String, String> map) {
        try {
            long start = System.currentTimeMillis();
            //分页相关
            Integer pageNum = Integer.parseInt(map.getOrDefault("pageNum", "1"));
            if (pageNum <= 0) {
                pageNum = 1;
            }
            Integer pageSize = Integer.parseInt(map.getOrDefault("pageSize", "5"));

            //时间查询
            Integer year = Integer.parseInt(map.getOrDefault("year", "-2"));
            Integer month = Integer.parseInt(map.getOrDefault("month", "-2"));
            Integer day = Integer.parseInt(map.getOrDefault("day", "-2"));
            //季度必须是[-1,1,2,3,4]或不传
            Integer quarter = Integer.parseInt(map.getOrDefault("quarter", "-1")); //季度应该转成月份列表
            List<Integer> quarterList = new LinkedList<>();
            switch (quarter) {
                case -1:
                    //相当于查询[1,12]月
                    for (int i = 1; i <= 12; i++) {
                        quarterList.add(i);
                    }
                    quarterList.add(-1);
                    break;
                case 1:
                    for (int i = 3; i <= 5; i++) {
                        quarterList.add(i);
                    }
                    break;
                case 2:
                    for (int i = 6; i <= 8; i++) {
                        quarterList.add(i);
                    }
                    break;
                case 3:
                    for (int i = 9; i <= 11; i++) {
                        quarterList.add(i);
                    }
                    break;
                case 4:
                    quarterList.add(12);
                    quarterList.add(1);
                    quarterList.add(2);
                    break;
                default:
                    Asserts.fail("季度输入有误");
            }

            Integer week = Integer.parseInt(map.getOrDefault("week", "-2"));

            //电影名称 返回的是电影列表 支持模糊查询 版本前端从里面寻找
            String title = map.get("title");

            //导演 不支持模糊查询，但是应该提供一个模糊查询导演名称的接口
            String director = map.get("director");

            //演员 不支持模糊查询，但是应该提供一个模糊查询演员名称的接口
            String actor = map.get("actor");

            //演员主演还是参演 默认是查询主演的电影
            boolean isStarring = Boolean.parseBoolean(map.getOrDefault("isStarring", "true"));
            ;

            //电影类别
            String genre = map.get("genre");

            //评分筛选，默认找出大于0分的
            Double score = Double.parseDouble(map.getOrDefault("score", "0"));

            //用户评价中有正面评价的电影
            //默认不靠这个筛选
            boolean hasPositive = Boolean.parseBoolean(map.getOrDefault("hasPositive", "false"));
            ;


            SearchInfo searchInfo = new SearchInfo(pageNum, pageSize, year, month, day,
                    quarterList, week, title, director, actor, isStarring, genre, score, hasPositive);


            Map<String, Object> result = movieService.searchMovie(searchInfo);
            long end = System.currentTimeMillis();
            result.put("time", end - start);
            return CommonResult.success(result);
        }
        catch (Exception e) {
            return CommonResult.failed("输入的参数格式有误!");
        }
    }


    /*
        //    提供一个模糊查询导演名称的接口
        @ApiOperation("根据导演名称模糊查询获取导演信息，这样可以先查询导演，然后根据导演全名查他对应的电影 也可以查询该导演导演的电影")
        @RequestMapping(value = "/directors", method = RequestMethod.POST)
        @ResponseBody
        public CommonResult<CommonPage<Director>> directors(@RequestBody Map<String, String> map) {
            try {
                long start = System.currentTimeMillis();
                //分页相关
                Integer pageNum = Integer.parseInt(map.getOrDefault("pageNum", "1"));
                if (pageNum <= 0) pageNum = 1;
                Integer pageSize = Integer.parseInt(map.getOrDefault("pageSize", "5"));
                //导演名称 支持模糊查询
                String name = map.getOrDefault("name", "");
                List<Director> list = movieService.directors(pageNum, pageSize, name);
                if (list == null || list.size() == 0) {
                    return CommonResult.failed("查询不到相关的导演信息!");
                }
                long end = System.currentTimeMillis();
                System.out.println(end - start);
                return CommonResult.success(CommonPage.restPage(list, end - start));
            }
            catch (Exception e) {
                return CommonResult.failed("输入的参数格式有误!");
            }
        }

        //    提供一个模糊查询演员名称的接口
        @ApiOperation("根据演员名称模糊查询获取演员信息，这样可以先查询演员，然后根据演员全名查他对应的电影  也可以快速查询该演员演的电影")
        @RequestMapping(value = "/actors", method = RequestMethod.POST)
        @ResponseBody
        public CommonResult<CommonPage<Actor>> actors(@RequestBody Map<String, String> map) {
            try {
                long start = System.currentTimeMillis();
                //分页相关
                Integer pageNum = Integer.parseInt(map.getOrDefault("pageNum", "1"));
                if (pageNum <= 0) pageNum = 1;
                Integer pageSize = Integer.parseInt(map.getOrDefault("pageSize", "5"));
                //演员名称 支持模糊查询
                String name = map.getOrDefault("name", "");
                List<Actor> list = movieService.actors(pageNum, pageSize, name);
                if (list == null || list.size() == 0) {
                    return CommonResult.failed("查询不到相关的演员信息!");
                }
                long end = System.currentTimeMillis();
                return CommonResult.success(CommonPage.restPage(list, end - start));
            }
            catch (Exception e) {
                return CommonResult.failed("输入的参数格式有误!");
            }
        }


        //    提供一个模糊查询题材名称的接口
        @ApiOperation("仅仅查询某个题材(支持模糊查询)的电影，为了速度")
        @RequestMapping(value = "/genre", method = RequestMethod.POST)
        @ResponseBody
        public CommonResult<CommonPage<Genre>> genre(@RequestBody Map<String, String> map) {
            try {
                long start = System.currentTimeMillis();
                //分页相关
                Integer pageNum = Integer.parseInt(map.getOrDefault("pageNum", "1"));
                if (pageNum <= 0) pageNum = 1;
                Integer pageSize = Integer.parseInt(map.getOrDefault("pageSize", "5"));
                String name = map.getOrDefault("name", "");
                List<Genre> list = movieService.genre(pageNum, pageSize, name);
                if (list == null || list.size() == 0) {
                    return CommonResult.failed("查询不到相关的题材信息!");
                }
                long end = System.currentTimeMillis();
                return CommonResult.success(CommonPage.restPage(list, end - start));
            }
            catch (Exception e) {
                return CommonResult.failed("输入的参数格式有误!");
            }
        }


        @ApiOperation("单独根据时间查询电影，为了速度")
        @RequestMapping(value = "/times", method = RequestMethod.POST)
        @ResponseBody
        public CommonResult<CommonPage<Time>> times(@RequestBody Map<String, String> map) {
            try {
                long start = System.currentTimeMillis();
                //分页相关
                Integer pageNum = Integer.parseInt(map.getOrDefault("pageNum", "1"));
                if (pageNum <= 0) pageNum = 1;
                Integer pageSize = Integer.parseInt(map.getOrDefault("pageSize", "5"));

                //时间查询
                Integer year = Integer.parseInt(map.getOrDefault("year", "-2"));
                Integer month = Integer.parseInt(map.getOrDefault("month", "-2"));
                Integer day = Integer.parseInt(map.getOrDefault("day", "-2"));
                //季度必须是[-1,1,2,3,4]或不传
                Integer quarter = Integer.parseInt(map.getOrDefault("quarter", "-1")); //季度应该转成月份列表
                List<Integer> quarterList = new LinkedList<>();
                switch (quarter) {
                    case -1:
                        //相当于查询[1,12]月
                        for (int i = 1; i <= 12; i++) {
                            quarterList.add(i);
                        }
                        break;
                    case 1:
                        for (int i = 3; i <= 5; i++) {
                            quarterList.add(i);
                        }
                        break;
                    case 2:
                        for (int i = 6; i <= 8; i++) {
                            quarterList.add(i);
                        }
                        break;
                    case 3:
                        for (int i = 9; i <= 11; i++) {
                            quarterList.add(i);
                        }
                        break;
                    case 4:
                        quarterList.add(12);
                        quarterList.add(1);
                        quarterList.add(2);
                        break;
                    default:
                        Asserts.fail("季度输入有误");
                }

                Integer week = Integer.parseInt(map.getOrDefault("week", "-2"));


                List<Time> list = movieService.times(pageNum, pageSize, year, month, day, week, quarterList);
                if (list == null || list.size() == 0) {
                    return CommonResult.failed("查询不到相关的时间信息!");
                }
                long end = System.currentTimeMillis();
                return CommonResult.success(CommonPage.restPage(list, end - start));
            }
            catch (Exception e) {
                return CommonResult.failed("输入的参数格式有误!");
            }
        }
    */
    @ApiOperation("获取电影详情")
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, Object>> detail(@PathVariable Integer id) {
        long start = System.currentTimeMillis();
        Movie movie = movieService.detail(id);
        if (movie == null) return CommonResult.failed("要查询的movie_id不存在!");
        Map<String, Object> result = new HashMap<>();

        if (movie.getLinkId().equals("##")) {
            result.put("linkId", "");
            result.put("linkTitle", "");
        }
        else {
            result.put("linkId", movie.getLinkId().replace("$$", ","));
            result.put("linkTitle", movie.getLinkTitle().replace("$$", ","));
        }
        movie.setSupportingActors(movie.getSupportingActors().replace("$$", ","));
        movie.setActor(movie.getActor().replace("$$", ","));
        movie.setGenres(movie.getGenres().replace("$$", ","));
        movie.setDirector(movie.getDirector().replace("$$", ","));
        result.put("movie", movie);
        long end = System.currentTimeMillis();
        result.put("time", end - start);
        return CommonResult.success(result);
    }

    /*
    - 按照演员和导演的关系进行查询及统计（例如经常合作的演员有哪些，经常合作的导演和演员有哪些）

        （1）输入演员，返回导演，合作次数

        （2）输入导演，返回演员，合作次数

        （3）输入演员，返回演员，合作次数
     */
    @ApiOperation("输入演员，返回导演，合作次数")
    @RequestMapping(value = "/actorToDirector", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, Object>> actorToDirector(@RequestBody Map<String, String> map) {
        long start = System.currentTimeMillis();

        String actorName = map.get("actorName");

        Map<String, Object> result = new HashMap<>();

        Map<String, Integer> collaborate = movieService.actorToDirector(actorName);
        result.put("collaborate", collaborate);
        result.put("total", collaborate.size());

        long end = System.currentTimeMillis();
        result.put("time", end - start);
        return CommonResult.success(result);
    }

    @ApiOperation("输入导演，返回演员，合作次数")
    @RequestMapping(value = "/directorToActor", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, Object>> directorToActor(@RequestBody Map<String, String> map) {
        long start = System.currentTimeMillis();

        String directorName = map.get("directorName");

        Map<String, Object> result = new HashMap<>();

        Map<String, Integer> collaborate = movieService.directorToActor(directorName);
        result.put("collaborate", collaborate);
        result.put("total", collaborate.size());

        long end = System.currentTimeMillis();
        result.put("time", end - start);
        return CommonResult.success(result);
    }

    @ApiOperation("输入演员，返回演员，合作次数")
    @RequestMapping(value = "/actorToActor", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, Object>> actorToActor(@RequestBody Map<String, String> map) {
        long start = System.currentTimeMillis();

        String actorName = map.get("actorName");

        Map<String, Object> result = new HashMap<>();

        Map<String, Integer> collaborate = movieService.actorToActor(actorName);
        result.put("collaborate", collaborate);
        result.put("total", collaborate.size());

        long end = System.currentTimeMillis();
        result.put("time", end - start);
        return CommonResult.success(result);
    }


    /*
    - 输入一个电影id，返回关于他所有的评论，
    - 评论总数，
    - list，取前15条，评论人，打分，好评/中立/差评
     */
    @ApiOperation("获取电影评价前15条")
    @RequestMapping(value = "/reviews/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, Object>> getReview(@PathVariable Integer id) {
        long start = System.currentTimeMillis();

        Map<String, Object> result = new HashMap<>();

        List<Map<String, Object>> reviews = movieService.getReview(id);

        result.put("reviews", reviews);
        result.put("total", reviews.size());
        long end = System.currentTimeMillis();
        result.put("time", end - start);
        return CommonResult.success(result);
    }
}
