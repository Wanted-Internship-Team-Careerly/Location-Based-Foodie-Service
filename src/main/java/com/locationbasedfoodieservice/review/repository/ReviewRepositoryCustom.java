package com.locationbasedfoodieservice.review.repository;

public interface ReviewRepositoryCustom {

    Integer findCountByRestaurantId(Long restaurantId);

}
