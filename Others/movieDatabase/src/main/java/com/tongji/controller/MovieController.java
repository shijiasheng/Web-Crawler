package com.tongji.controller;

import com.tongji.service.MovieService;
import com.tongji.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    year,month,quarter,week
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

    @ApiOperation("按照时间进行查询及统计")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public String query() {
        return "ABC";
    }


    @Autowired
    private TestService testService;

    @ApiOperation("开始插入数据库")
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public String test() throws ParseException {
        System.out.println("开始");
        testService.addMovie();
        return "ABC";
    }



}
