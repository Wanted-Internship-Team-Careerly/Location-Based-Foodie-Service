package com.locationbasedfoodieservice.restaurant.controller;

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

}
