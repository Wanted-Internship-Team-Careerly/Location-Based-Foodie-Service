package com.locationbasedfoodieservice.restaurant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import org.springframework.stereotype.Repository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryCustom{

}
