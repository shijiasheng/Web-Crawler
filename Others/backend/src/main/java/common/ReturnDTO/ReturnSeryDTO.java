package common.ReturnDTO;

import backend.pojo.neo4j.Sery;
import lombok.Data;

import java.util.List;

/**
 * @author: OY
 * @date: 15:46 2019/12/22
 */
@Data
public class ReturnSeryDTO {
    String seryTitle;
    List<Sery> movies;
}
