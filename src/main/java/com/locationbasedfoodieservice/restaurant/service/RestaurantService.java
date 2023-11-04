package com.locationbasedfoodieservice.restaurant.service;

import com.locationbasedfoodieservice.restaurant.dto.RestaurantsResponseDto;
import com.locationbasedfoodieservice.restaurant.repository.RestaurantRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Restaurant API", description = "Restaurant 관련 API 정보를 담고 있습니다.")
@RequiredArgsConstructor
@RestController
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Transactional(readOnly = true)
    public RestaurantsResponseDto getRestaurantsBySigungu(String sigungu, String sort, int range){
       RestaurantsResponseDto restaurantsResponseDto = null;
       return restaurantsResponseDto;
    }
}
