package com.locationbasedfoodieservice.restaurant.controller;

import com.locationbasedfoodieservice.restaurant.dto.RestaurantsResponseDto;
import com.locationbasedfoodieservice.restaurant.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "restaurantAPI", description = "시군구 기반 목록, 위치 기반 목록, 평가 및 상세 정보 API")
@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/api/restaurants/list/location")
    @Operation(summary = "시군구 기반 맛집 리스트 api. 거리 단위 : meter")
    public ResponseEntity<?> getRestaurantsBySigungu(
        @RequestParam() String sigungu,
        @RequestParam(name = "sort", defaultValue = "거리순", required = false) String sort,
        @RequestParam(defaultValue = "1000.0", required = false) int range) {
        RestaurantsResponseDto restaurants = restaurantService.getRestaurantsBySigungu(sigungu,
            sort, range);
        return ResponseEntity.ok().body(restaurants);
    }
}
