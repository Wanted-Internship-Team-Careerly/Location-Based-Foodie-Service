package com.locationbasedfoodieservice.restaurant.repository;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import java.util.List;
import org.springframework.stereotype.Repository;

public interface RestaurantRepositoryCustom {

    List<Restaurant> findwithReviewBasedSigungu(
      String sigungu
    );

    List<Restaurant> findwithReviewBasedPosition(
        double lon, double lat
    );
}
