package common.ReturnDTO;

import lombok.Data;

import java.util.Set;

/**
 * @author: OY
 * @date: 19:05 2019/12/12
 */
@Data
public class ReturnProductDTO {
    private Set<String> language;

    private String binding;

    private String runtime;

    private String rate;

    private String productId;

    private String release_year;

    private String release_month;

    private String release_day;

    private String release_weekday;

    private String good_comment;

    private String good_comment_rate;

    private String total_comment;
}
