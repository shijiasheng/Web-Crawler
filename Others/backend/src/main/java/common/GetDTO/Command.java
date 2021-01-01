package common.GetDTO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: OY
 * @date: 22:33 2019/12/9
 */
@Data
public class Command implements Serializable {

    private String operator;

    private String field;

    private String condition;

    private String value;

}
