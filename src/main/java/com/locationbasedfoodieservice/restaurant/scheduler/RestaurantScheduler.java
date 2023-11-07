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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Scheduled(cron = "0 34 22 * * *")
	public void updateRestaurant() {
		log.info("Data PreProcessing Start");

		List<RawRestaurant> rawRestaurants = rawRestaurantRepository.findAll();
		Map<String, Restaurant> restaurants = new HashMap<>();

		for (RawRestaurant rawRestaurant : rawRestaurants) {
			String name = rawRestaurant.getBizplcNm();
			String address = rawRestaurant.getRefineRoadnmAddr();
			String nameAddress = name + address;
			String uniqueKey = nameAddress.replaceAll(" ", "");

			Restaurant restaurant = restaurantRepository.findByNameAddress(uniqueKey)
					.orElse(null);

			if (restaurant == null) {
				restaurants.put(uniqueKey, rawRestaurant.toRestaurant(uniqueKey));
				continue;
			}

			restaurant.update(rawRestaurant);
		}

		restaurantRepository.saveAll(restaurants.values());

		log.info("Data Processing End");
	}
}
