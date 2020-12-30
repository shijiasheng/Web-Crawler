package com.tongji.controller;

import com.tongji.common.api.CommonPage;
import com.tongji.common.api.CommonResult;
import com.tongji.common.exception.Asserts;
import com.tongji.dto.SearchInfo;
import com.tongji.model.Movie;
import com.tongji.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public CommonResult<CommonPage<Movie>> searchMovie(@RequestBody Map<String, String> map) {
        try {
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


            List<Movie> list = movieService.searchMovie(searchInfo);
            if (list == null || list.size() == 0) {
                return CommonResult.failed("查询不到相关的电影信息!");
            }
            return CommonResult.success(CommonPage.restPage(list));
        }
        catch (Exception e) {
            return CommonResult.failed("输入的参数格式有误!");
        }
    }

//按照演员和导演的关系进行查询及统计（例如经常合作的演员有哪些，经常合作的导演和演员有哪些）
// 用户评价中有正面评价的电影有哪些等
//    提供一个模糊查询导演名称的接口
//    提供一个模糊查询演员名称的接口

//    @Autowired
//    private TestService testService;
//
//    @ApiOperation("开始插入数据库")
//    @RequestMapping(value = "/test", method = RequestMethod.POST)
//    @ResponseBody
//    public String test() throws ParseException {
//        System.out.println("开始");
//        testService.addMovie();
//        return "ABC";
//    }
}
