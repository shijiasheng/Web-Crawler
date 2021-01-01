package backend.controller.hive;

import backend.pojo.hive.HiveMovie;
import backend.pojo.hive.HiveReview;
import backend.service.hive.HiveMovieService;
import common.GetDTO.GetMoviesDTO;
import common.ReturnDTO.ReturnDTO;
import common.ReturnDTO.ReturnMovieDTO;
import common.ReturnDTO.ReturnReviewsDTO;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hive2")
public class HiveController {

    HiveMovieService hiveMovieService = new HiveMovieService();

    @Autowired
    @Qualifier("hiveJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/movies")
    public ResponseEntity<ReturnDTO> getMoviesByHive(@RequestBody @Validated @ApiParam("筛选数据")GetMoviesDTO getMoviesDTO){
        return new ResponseEntity<>(hiveMovieService.getMoviesByHive(getMoviesDTO,jdbcTemplate), HttpStatus.OK);
    }

    @GetMapping("/reviews/{productID}")
    public ResponseEntity<ReturnReviewsDTO> getReviewByProductID(@PathVariable("productID") String productID){
        return new ResponseEntity<>(hiveMovieService.getReviewByProdutID(productID,jdbcTemplate),HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<Map<String, Object>> list() {
        String sql = "select * from act where actor = 'Leepy Hollow High'";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }


}
