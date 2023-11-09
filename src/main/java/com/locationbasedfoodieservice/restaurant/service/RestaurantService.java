package com.locationbasedfoodieservice.restaurant.service;

import com.locationbasedfoodieservice.common.error.CustomErrorCode;
import com.locationbasedfoodieservice.common.exception.CustomException;
import com.locationbasedfoodieservice.restaurant.dto.response.RestaurantResponseDto;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.restaurant.repository.RestaurantRepository;
import com.locationbasedfoodieservice.review.entity.Review;
import com.locationbasedfoodieservice.review.repository.ReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public RestaurantResponseDto getRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new CustomException(CustomErrorCode.RESTAURANT_NOT_FOUND));

        List<Review> reviewList = reviewRepository.findReviewsByRestaurantId(restaurantId);

        return RestaurantResponseDto.from(restaurant, reviewList);
    }
}
