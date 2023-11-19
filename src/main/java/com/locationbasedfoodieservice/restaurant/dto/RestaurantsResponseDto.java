package com.locationbasedfoodieservice.restaurant.dto;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RestaurantsResponseDto {

    private List<RestaurantDetailResponseDto> data;

    public static RestaurantsResponseDto from(List<RestaurantDetailResponseDto> list){
        return RestaurantsResponseDto.builder()
            .data(list)
            .build();
    }
}
