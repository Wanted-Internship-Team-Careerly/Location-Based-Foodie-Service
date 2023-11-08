package com.locationbasedfoodieservice.restaurant.repository;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	Optional<Restaurant> findByNameAddress(String nameAddress);
}
