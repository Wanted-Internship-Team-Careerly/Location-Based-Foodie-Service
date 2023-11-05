package com.locationbasedfoodieservice.restaurant.dto;

import com.locationbasedfoodieservice.review.dto.response.ReviewResponseDto;
import java.util.List;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class RestaurantDetailResponseDto {

    private String city;
    private String name;
    private String licenseDate;
    private String businessStatus;
    private String type;
    private String streetAddress;
    private String lotNumberAddress;
    private String postalCode;
    private Double latitude;
    private Double longitude;
    private String nameAddress;
    private List<ReviewResponseDto> reviewList;

    public static RestaurantDetailResponseDto from(Restaurant restaurant,
        List<ReviewResponseDto> reviewList) {
        return RestaurantDetailResponseDto.builder()
            .city(restaurant.getCity())
            .name(restaurant.getName())
            .licenseDate(restaurant.getLicenseDate())
            .businessStatus(restaurant.getBusinessStatus())
            .type(restaurant.getType())
            .streetAddress(restaurant.getStreetAddress())
            .lotNumberAddress(restaurant.getLotNumberAddress())
            .postalCode(restaurant.getPostalCode())
            .latitude(restaurant.getLatitude())
            .longitude(restaurant.getLongitude())
            .nameAddress(restaurant.getNameAddress())
            .reviewList(reviewList)
            .build();
    }

    public static RestaurantDetailResponseDto from(Restaurant restaurant) {
        return RestaurantDetailResponseDto.builder()
            .city(restaurant.getCity())
            .name(restaurant.getName())
            .licenseDate(restaurant.getLicenseDate())
            .businessStatus(restaurant.getBusinessStatus())
            .type(restaurant.getType())
            .streetAddress(restaurant.getStreetAddress())
            .lotNumberAddress(restaurant.getLotNumberAddress())
            .postalCode(restaurant.getPostalCode())
            .latitude(restaurant.getLatitude())
            .longitude(restaurant.getLongitude())
            .nameAddress(restaurant.getNameAddress())
            .build();
    }
}
