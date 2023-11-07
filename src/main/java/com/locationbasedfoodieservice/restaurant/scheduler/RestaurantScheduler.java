package com.locationbasedfoodieservice.restaurant.scheduler;

import com.locationbasedfoodieservice.rawrestaurant.entity.RawRestaurant;
import com.locationbasedfoodieservice.rawrestaurant.repository.RawRestaurantRepository;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "PreProcessing Data Scheduling")
@Component
@Transactional
@RequiredArgsConstructor
public class RestaurantScheduler {
	private final RestaurantRepository restaurantRepository;
	private final RawRestaurantRepository rawRestaurantRepository;

	/**
	 * 토요일 새벽 4시에 업데이트 되는 rawRestaurant 데이터를 사용하여
	 * Restaurant를 업데이트해주는 메서드
	 */
	@Scheduled(cron = "0 5 4 * * 6")
	public void updateRestaurant() {
		log.info("Data PreProcessing Start");

		List<RawRestaurant> rawRestaurants = rawRestaurantRepository.findAll();
		List<Restaurant> restaurants = new ArrayList<>();

		for (RawRestaurant rawRestaurant : rawRestaurants) {
			String name = rawRestaurant.getBizplcNm();
			String address = rawRestaurant.getRefineRoadnmAddr();

			Restaurant restaurant = restaurantRepository.findByNameAddress(name + address)
					.orElse(null);
			if (restaurant == null) {
				restaurants.add(rawRestaurant.toRestaurant());
				continue;
			}
			restaurant.update(rawRestaurant);
		}

		restaurantRepository.saveAll(restaurants);
	}
}
