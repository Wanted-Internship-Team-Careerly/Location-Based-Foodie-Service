package com.locationbasedfoodieservice.restaurant.dto.response;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.review.entity.Review;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class RestaurantResponseDto {

    private Restaurant restaurant;
    private List<Review> reviewList;

    public static RestaurantResponseDto from(Restaurant restaurant, List<Review> reviewList) {
        return RestaurantResponseDto.builder()
                .restaurant(restaurant)
                .reviewList(reviewList)
                .build();
    }

}
