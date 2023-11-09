package com.locationbasedfoodieservice.review.repository;

import com.locationbasedfoodieservice.review.entity.Review;
import java.util.List;

public interface ReviewRepositoryCustom {

    Integer findCountByRestaurantId(Long restaurantId);

    List<Review> findReviewsByRestaurantId(Long restaurantId);

}
