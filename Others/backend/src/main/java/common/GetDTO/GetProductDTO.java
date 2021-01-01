package common.GetDTO;

import lombok.Data;

import java.util.List;

/**
 * @author: OY
 * @date: 14:32 2019/12/21
 */
@Data
public class GetProductDTO {

    private List<Command> commands;
    String title;
}
