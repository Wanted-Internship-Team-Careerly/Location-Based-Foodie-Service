package com.locationbasedfoodieservice.restaurant.controller;

import com.locationbasedfoodieservice.restaurant.dto.response.RestaurantResponseDto;
import com.locationbasedfoodieservice.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping(value = "/{restaurantId}")
    public ResponseEntity<RestaurantResponseDto> getRestaurant(@PathVariable("restaurantId") Long restaurantId) {

        return ResponseEntity.ok().body(restaurantService.getRestaurant(restaurantId));
    }

}
