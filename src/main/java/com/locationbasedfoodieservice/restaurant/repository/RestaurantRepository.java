package com.locationbasedfoodieservice.restaurant.repository;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom {

    Optional<Restaurant> findByNameAddress(String nameAddress);

}
