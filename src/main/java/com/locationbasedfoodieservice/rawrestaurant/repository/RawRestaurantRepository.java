package com.locationbasedfoodieservice.rawrestaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.locationbasedfoodieservice.rawrestaurant.entity.RawRestaurant;

@Repository
public interface RawRestaurantRepository extends JpaRepository<RawRestaurant, Long> {
}