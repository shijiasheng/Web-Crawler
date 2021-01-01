package backend.pojo.mysql;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LimitMovie {

    private String title;

    private String genre;

    private String actor;

    private String director;
}
