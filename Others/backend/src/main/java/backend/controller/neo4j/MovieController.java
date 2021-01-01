package backend.controller.neo4j;

import backend.pojo.neo4j.Movie;
import backend.service.neo4j.MovieService;
import common.GetDTO.*;
import common.ReturnDTO.ReturnCooperationDTO;
import common.ReturnDTO.ReturnDTO;
import common.ReturnDTO.ReturnStasticDTO;
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

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = {"/movie"})
@Api(tags = {"Neo4j--电影相关"})
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping(value = {"/getMovieByProductId"})
    @ApiOperation(value = "根据product_id获取电影")
    public ResponseEntity<ReturnDTO> getMovieByProductId(@RequestBody String id) {
        return new ResponseEntity<ReturnDTO>(movieService.getMovieByProductId(id), HttpStatus.OK);
    }

    @PostMapping(value = {"/allMatch"})
    @ApiOperation(value = "获取符合条件的Movie数量及前十条电影")
    public ResponseEntity<ReturnDTO> allMatch(@RequestBody @Validated @ApiParam("筛选数据") GetMoviesDTO getMoviesDTO) {
        return new ResponseEntity<ReturnDTO>(movieService.getAllMatchSimple(getMoviesDTO), HttpStatus.OK);
    }

//    @PostMapping(value = "/getPageMovies")
//    @ApiOperation(value = "获取10条指定page页的Movie ")
//    public ResponseEntity<ReturnDTO> pageMovie(@RequestBody @Validated @ApiParam("筛选数据") GetPageMovieDTO getPageMovieDTO){
//        return new ResponseEntity<>(movieService.getPageMovies(getPageMovieDTO), HttpStatus.OK);
//    }

    @PostMapping(value = {"/getProduct"})
    @ApiOperation(value = "获取产品详细信息")
    public ResponseEntity<ReturnDTO> allMatch(@RequestBody @Validated @ApiParam("筛选数据") GetProductDTO getProductDTO) {
        return new ResponseEntity<ReturnDTO>(movieService.getMatchProductSimple(getProductDTO), HttpStatus.OK);
    }

    @PostMapping(value = {"/tenMovie"})
    @ApiOperation(value = "test---获取10个movie")
    public ResponseEntity<List<Movie>> getTenMovie() {
        return new ResponseEntity<>(movieService.getTenMovie(), HttpStatus.OK);
    }

    @PostMapping(value = {"/getCooperation"})
    @ApiOperation(value = "获取合作信息")
    public ResponseEntity<ReturnDTO> getCooperation(@RequestBody @Validated @ApiParam("获取合作信息") GetCooperationDTO getCooperationDTO) {
        return new ResponseEntity<>(movieService.getCooperation(getCooperationDTO), HttpStatus.OK);
    }

    @PostMapping(value = {"/getTopCooperation"})
    @ApiOperation(value = "获取Top合作信息")
    public ResponseEntity<ReturnDTO> getTopCooperation(@RequestBody @Validated @ApiParam("获取Top合作信息") GetTopCooperationDTO getTopCooperationDTO) {
        return new ResponseEntity<>(movieService.getTopCooperation(getTopCooperationDTO), HttpStatus.OK);
    }

    @PostMapping(value = {"/getStastic"})
    @ApiOperation(value = "获取统计信息")
    public ResponseEntity<ReturnDTO> getStastic(@RequestBody @Validated @ApiParam("获取统计信息") GetStasticDTO getStasticDTO) {
        return new ResponseEntity<>(movieService.getStastic(getStasticDTO), HttpStatus.OK);
    }

    @PostMapping(value = {"/getSery"})
    @ApiOperation(value = "查询系列电影")
    public ResponseEntity<ReturnDTO> getSery(@RequestBody @Validated @ApiParam("查询系列电影") GetSeryDTO getSeryDTO) {
        return new ResponseEntity<>(movieService.getSery(getSeryDTO), HttpStatus.OK);
    }

    @PostMapping(value = {"/getTimeStastic"})
    @ApiOperation(value = "获取时间统计信息")
    public ResponseEntity<ReturnDTO> getTimeStastic(@RequestBody @Validated @ApiParam("获取时间统计信息") GetTimeDTO getTimeDTO) {
        return new ResponseEntity<>(movieService.getTime(getTimeDTO), HttpStatus.OK);
    }
}
