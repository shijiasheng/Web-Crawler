package com.tongji.service.impl;

import com.github.pagehelper.PageHelper;
import com.tongji.dto.SearchInfo;
import com.tongji.mapper.MovieMapper;
import com.tongji.mapper.TimeMapper;
import com.tongji.model.Movie;
import com.tongji.model.MovieExample;
import com.tongji.model.Time;
import com.tongji.model.TimeExample;
import com.tongji.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private TimeMapper timeMapper;

    @Override
    public List<Movie> searchMovie(SearchInfo searchInfo) {
        MovieExample movieExample = new MovieExample();
        MovieExample.Criteria criteria = movieExample.createCriteria();

        //按照时间查询
        TimeExample timeExample = new TimeExample();
        TimeExample.Criteria timeExampleCriteria = timeExample.createCriteria();
        //没传入的信息则不用查
        if (searchInfo.getYear() != -2) {
            timeExampleCriteria.andYearEqualTo(searchInfo.getYear());
        }
        if (searchInfo.getMonth() != -2) {
            timeExampleCriteria.andMonthEqualTo(searchInfo.getMonth());
        }
        if (searchInfo.getDay() != -2) {
            timeExampleCriteria.andDayEqualTo(searchInfo.getDay());
        }
        if (searchInfo.getWeek() != -2) {
            timeExampleCriteria.andWeekEqualTo(searchInfo.getWeek());
        }
        //季度查询
        timeExampleCriteria.andMonthIn(searchInfo.getQuarterList());
        //查询time这几条就够了
        PageHelper.startPage(searchInfo.getPageNum(), searchInfo.getPageSize());//分页相关
        List<Time> times = timeMapper.selectByExample(timeExample);
        System.out.println(times);

        

        return null;
    }
}
