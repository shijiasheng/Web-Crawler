package common.ReturnDTO;

import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * @author: OY
 * @date: 19:06 2019/12/12
 */
@Data
public class ReturnMovieDTO {
    private String title;

    private Set<String> actor;

    private Set<String> director;

    private Set<String> genre;

    private int version;

    private Map<String,ReturnProductDTO> product;
}
