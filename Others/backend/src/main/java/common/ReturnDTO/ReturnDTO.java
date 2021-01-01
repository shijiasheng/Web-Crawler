package common.ReturnDTO;

import lombok.*;

import java.io.Serializable;

/**
 * @author: OY
 * @date: 18:38 2019/12/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnDTO implements Serializable {
    private long totalTime;
    private long queryTime;
    private int movieNum;
    private int productNum;
    private int length;
    private Object data;

}
