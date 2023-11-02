package com.locationbasedfoodieservice.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

}
