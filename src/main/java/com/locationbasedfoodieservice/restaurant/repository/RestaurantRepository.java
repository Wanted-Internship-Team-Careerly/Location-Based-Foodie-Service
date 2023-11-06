package com.locationbasedfoodieservice.restaurant.repository;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
