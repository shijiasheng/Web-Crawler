package backend.dao.mysql;

import backend.pojo.mysql.LimitMovie;
import backend.pojo.mysql.MatchCount;
import backend.pojo.mysql.MysqlMovie;
import common.GetDTO.Command;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MysqlMovieMapper {

    MatchCount getMovies(@Param("commands") List<Command> commands);

    MatchCount getMatchCount(@Param("commands") List<Command> commands);

    List<LimitMovie> getLimitMovies(@Param("commands") List<Command> commands,
                                    @Param("start") int start, @Param("end") int end);

    MatchCount getMatchCountByLanguage(@Param("language") String language);

    List<LimitMovie> getLimitMoviesByLanguage(@Param("language") String language,
                                    @Param("start") int start, @Param("end") int end);

    MatchCount getMatchCountByArr(@Param("commands") List<Command> commands);

    List<LimitMovie> getLimitMoviesByArr(@Param("commands") List<Command> commands,
                                    @Param("start") int start, @Param("end") int end);

    List<String> getGenres();

    List<String> getLanguages();

    List<String> getBindings();

}
