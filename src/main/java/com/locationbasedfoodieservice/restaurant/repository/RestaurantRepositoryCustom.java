package com.locationbasedfoodieservice.restaurant.repository;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import java.util.List;
import org.springframework.stereotype.Repository;

public interface RestaurantRepositoryCustom {

    List<Restaurant> findBySigungu(
      String sigungu, boolean orderByRating
    );

    List<Restaurant> findByPosition(
        double lon, double lat, boolean orderByRating
    );
}
