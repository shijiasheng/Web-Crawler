package common.ReturnDTO;

import backend.pojo.hive.HiveReview;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReturnReviewsDTO {
    List<HiveReview> hiveReviews;
    long queryTime;
}
