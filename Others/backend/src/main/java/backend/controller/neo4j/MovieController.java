package backend.controller.neo4j;

import backend.pojo.neo4j.Movie;
import backend.service.neo4j.MovieService;
import common.*;
import common.GetDTO.*;
import common.ReturnDTO.ReturnDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = {"/movie"})
@Api(tags = {"Neo4j--电影相关"})
public class MovieController
{
    @Autowired
    private MovieService movieService;

//    年
//    月
//    日
//    星期
//    季度
//    导演
//    主演
//    参演
//    电影类别
//    评分

    @PostMapping(value = {"/getDetailMovie"})
    @ApiOperation(value = "获取某电影的详细信息")
    public CommonResult<DetailMovieResult> getDetailMovie(@RequestBody String product_id)
    {
        return CommonResult.success(movieService.getDetailMovie(product_id));
    }


    @PostMapping(value = {"/getMovie"})
    @ApiOperation(value = "获取电影")
    public CommonResult<List<ReturnMovieResult>> getMovie(@RequestBody Map<String, String> map)
    {
        long start = System.currentTimeMillis();
        System.out.println(map);
        System.out.println(map.get("director"));
        int pageNum = Integer.parseInt(map.getOrDefault("pageNum", "1"));
        if (pageNum <= 0)
        {
            pageNum = 1;
        }
        int pageSize = Integer.parseInt(map.getOrDefault("pageSize", "5"));
        SearchCommand searchCommand = new SearchCommand();
        searchCommand.setLimit(pageSize);
        searchCommand.setSkip(pageSize * (pageNum - 1));
        searchCommand.setYear(map.getOrDefault("year", ""));
        searchCommand.setMonth(map.getOrDefault("month", ""));
        searchCommand.setDay(map.getOrDefault("day", ""));
        searchCommand.setWeek(map.getOrDefault("week", ""));
        searchCommand.setDirector(map.getOrDefault("director", ""));
        searchCommand.setActor(map.getOrDefault("actor", ""));
        searchCommand.setIs_supporting(map.getOrDefault("is_supporting", ""));
        searchCommand.setGenre(map.getOrDefault("genre", ""));
        searchCommand.setStar(map.getOrDefault("star", ""));

        String quarter = map.getOrDefault("quarter", "");
        if (!quarter.equals(""))
        {
            List<String> months = new LinkedList<>();
            switch (quarter)
            {
                case "1":
                    for (int i = 3; i <= 5; i++)
                    {
                        months.add(String.valueOf(i));
                    }
                    break;
                case "2":
                    for (int i = 6; i <= 8; i++)
                    {
                        months.add(String.valueOf(i));
                    }
                    break;
                case "3":
                    for (int i = 9; i <= 11; i++)
                    {
                        months.add(String.valueOf(i));
                    }
                    break;
                case "4":
                    months.add(String.valueOf(12));
                    months.add(String.valueOf(1));
                    months.add(String.valueOf(2));
                    break;
                default:
                    System.out.println("!!!");
                    break;
            }
            searchCommand.setSearchQuarter(1);
            searchCommand.setMonths(months);
        } else
        {
            searchCommand.setSearchQuarter(0);
        }
        long end = System.currentTimeMillis();
        int total = movieService.getMovieCount(searchCommand);
        System.out.print("time ");
        System.out.println(end - start);
        System.out.print("total ");
        System.out.println(total);
        return CommonResult.success(movieService.getMovie(searchCommand), (end - start), total);

    }

    @PostMapping(value = {"/getDirectorByActor"})
    @ApiOperation(value = "输入演员，返回导演，合作次数")
    public CommonResult<List<ReturnDirectorResult>> getDirectorByActor(@RequestBody String actor)
    {
        return CommonResult.success(movieService.getDirectorByActor(actor));
    }

    @PostMapping(value = {"/getActorByDirector"})
    @ApiOperation(value = "输入导演，返回演员，合作次数")
    public CommonResult<List<ReturnActorResult>> getActorByDirector(@RequestBody String director)
    {
        return CommonResult.success(movieService.getActorByDirector(director));
    }

    @PostMapping(value = {"/getActorByActor"})
    @ApiOperation(value = "输入演员，返回演员，合作次数")
    public CommonResult<List<ReturnActorResult>> getActorByActor(@RequestBody String actor)
    {
        return CommonResult.success(movieService.getActorByActor(actor));
    }

//    @PostMapping(value = {"/getMovieByDirector"})
//    @ApiOperation(value = "获取某导演的所有电影")
//    public CommonResult<List<ReturnMovieResult>> getMovieByDirector(@RequestBody Map<String, String> map)
//    {
//        String directorName = map.get("director");
//        return CommonResult.success(movieService.getMovieByDirector(directorName));
//    }

//    @PostMapping(value = {"/getMovieByProductId"})
//    @ApiOperation(value = "根据product_id获取电影")
//    public ResponseEntity<ReturnDTO> getMovieByProductId(@RequestBody String id)
//    {
//        return new ResponseEntity<ReturnDTO>(movieService.getMovieByProductId(id), HttpStatus.OK);
//    }

//    @PostMapping(value = {"/allMatch"})
//    @ApiOperation(value = "获取符合条件的Movie数量及前十条电影")
//    public ResponseEntity<ReturnDTO> allMatch(@RequestBody @Validated @ApiParam("筛选数据") GetMoviesDTO getMoviesDTO)
//    {
//        return new ResponseEntity<ReturnDTO>(movieService.getAllMatchSimple(getMoviesDTO), HttpStatus.OK);
//    }

//    @PostMapping(value = "/getPageMovies")
//    @ApiOperation(value = "获取10条指定page页的Movie ")
//    public ResponseEntity<ReturnDTO> pageMovie(@RequestBody @Validated @ApiParam("筛选数据") GetPageMovieDTO getPageMovieDTO){
//        return new ResponseEntity<>(movieService.getPageMovies(getPageMovieDTO), HttpStatus.OK);
//    }

//    @PostMapping(value = {"/getProduct"})
//    @ApiOperation(value = "获取产品详细信息")
//    public ResponseEntity<ReturnDTO> allMatch(@RequestBody @Validated @ApiParam("筛选数据") GetProductDTO getProductDTO)
//    {
//        return new ResponseEntity<ReturnDTO>(movieService.getMatchProductSimple(getProductDTO), HttpStatus.OK);
//    }

//    @PostMapping(value = {"/tenMovie"})
//    @ApiOperation(value = "test---获取10个movie")
//    public ResponseEntity<List<Movie>> getTenMovie()
//    {
//        return new ResponseEntity<>(movieService.getTenMovie(), HttpStatus.OK);
//    }
//
//    @PostMapping(value = {"/getCooperation"})
//    @ApiOperation(value = "获取合作信息")
//    public ResponseEntity<ReturnDTO> getCooperation(@RequestBody @Validated @ApiParam("获取合作信息") GetCooperationDTO getCooperationDTO)
//    {
//        return new ResponseEntity<>(movieService.getCooperation(getCooperationDTO), HttpStatus.OK);
//    }

//    @PostMapping(value = {"/getTopCooperation"})
//    @ApiOperation(value = "获取Top合作信息")
//    public ResponseEntity<ReturnDTO> getTopCooperation(@RequestBody @Validated @ApiParam("获取Top合作信息") GetTopCooperationDTO getTopCooperationDTO)
//    {
//        return new ResponseEntity<>(movieService.getTopCooperation(getTopCooperationDTO), HttpStatus.OK);
//    }
//
//    @PostMapping(value = {"/getStastic"})
//    @ApiOperation(value = "获取统计信息")
//    public ResponseEntity<ReturnDTO> getStastic(@RequestBody @Validated @ApiParam("获取统计信息") GetStasticDTO getStasticDTO)
//    {
//        return new ResponseEntity<>(movieService.getStastic(getStasticDTO), HttpStatus.OK);
//    }

//    @PostMapping(value = {"/getSery"})
//    @ApiOperation(value = "查询系列电影")
//    public ResponseEntity<ReturnDTO> getSery(@RequestBody @Validated @ApiParam("查询系列电影") GetSeryDTO getSeryDTO)
//    {
//        return new ResponseEntity<>(movieService.getSery(getSeryDTO), HttpStatus.OK);
//    }
//
//    @PostMapping(value = {"/getTimeStastic"})
//    @ApiOperation(value = "获取时间统计信息")
//    public ResponseEntity<ReturnDTO> getTimeStastic(@RequestBody @Validated @ApiParam("获取时间统计信息") GetTimeDTO getTimeDTO)
//    {
//        return new ResponseEntity<>(movieService.getTime(getTimeDTO), HttpStatus.OK);
//    }
}
