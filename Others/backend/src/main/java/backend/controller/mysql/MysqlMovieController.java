package backend.controller.mysql;

import backend.service.mysql.MysqlMovieService;
import common.GetDTO.GetMoviesDTO;
import common.GetDTO.GetPageMovieDTO;
import common.ReturnDTO.ReturnDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mysqlMovie")
@Api(tags = {"Mysql--电影相关"})
public class MysqlMovieController {

    @Autowired
    MysqlMovieService mysqlMovieService;

    @PostMapping(value = {"/allMatch"})
    @ApiOperation(value = "获取电影详细信息(全Join)")
    public ResponseEntity<ReturnDTO> allMatch(@RequestBody @Validated @ApiParam("筛选数据") GetMoviesDTO getMoviesDTO){
        return new ResponseEntity<>(mysqlMovieService.getAllMatch(getMoviesDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/getMovies")
    @ApiOperation(value = "获取查询数量及前十条电影")
    public ResponseEntity<ReturnDTO> matchCount(@RequestBody @Validated @ApiParam("筛选数据") GetMoviesDTO getMoviesDTO){
        return new ResponseEntity<>(mysqlMovieService.getMovies(getMoviesDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/getMoviesByArr")
    @ApiOperation(value = "通过数组查询数量及前十条电影")
    public ResponseEntity<ReturnDTO> matchCountByArr(@RequestBody @Validated @ApiParam("筛选数据") GetMoviesDTO getMoviesDTO){
        return new ResponseEntity<>(mysqlMovieService.getMoviesByArr(getMoviesDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/getPageMovies")
    @ApiOperation(value = "获取page页数据")
    public ResponseEntity<ReturnDTO> pageMovie(@RequestBody @Validated @ApiParam("筛选数据") GetPageMovieDTO getPageMovieDTO){
        return new ResponseEntity<>(mysqlMovieService.getPageMovies(getPageMovieDTO), HttpStatus.OK);
    }

    @GetMapping(value = {"/genres"})
    @ApiOperation(value = "获取电影类别")
    public ResponseEntity<List<String>> getGenres(){
        return new ResponseEntity<>(mysqlMovieService.getGenres(), HttpStatus.OK);
    }

    @GetMapping(value = {"/languages"})
    @ApiOperation(value = "获取电影语言")
    public ResponseEntity<List<String>> getLanguage(){
        return new ResponseEntity<>(mysqlMovieService.getLanguages(), HttpStatus.OK);
    }

    @GetMapping(value = {"/bindings"})
    @ApiOperation(value = "获取电影介质")
    public ResponseEntity<List<String>> getBinding(){
        return new ResponseEntity<>(mysqlMovieService.getBindings(), HttpStatus.OK);
    }

}
