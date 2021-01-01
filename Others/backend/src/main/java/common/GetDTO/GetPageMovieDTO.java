package common.GetDTO;

import lombok.Data;

import java.util.List;

@Data
public class GetPageMovieDTO {

    private int page;

    private List<Command> commands;

}
