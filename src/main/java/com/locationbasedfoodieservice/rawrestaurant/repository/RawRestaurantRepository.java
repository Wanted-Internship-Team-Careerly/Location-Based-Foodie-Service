package com.locationbasedfoodieservice.rawrestaurant.repository;

import com.locationbasedfoodieservice.rawrestaurant.entity.RawRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RawRestaurantRepository extends JpaRepository<RawRestaurant, Long> {
	Optional<RawRestaurant> findByBizplcNmAndRefineZipCd(String name, String zip);
}
