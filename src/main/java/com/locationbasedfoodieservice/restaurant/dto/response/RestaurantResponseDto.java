package com.locationbasedfoodieservice.restaurant.dto.response;

import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.review.dto.response.ReviewResponseDto;
import com.locationbasedfoodieservice.review.entity.Review;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class RestaurantResponseDto {

    private Long restaurantId;
    private String nameAddress;
    private String city;
    private String name;
    private String licenseDate;
    private String businessStatus;
    private String type;
    private String streetAddress;
    private String lotNumberAddress;
    private String postalCode;
    private double longitude;
    private double latitude;
    private double rating;
    private List<ReviewResponseDto> reviewList;

    // 가게 정보와 해당 가게에 작성된 review 들을 return
    public static RestaurantResponseDto from(Restaurant restaurant, List<ReviewResponseDto> reviewList) {
        return RestaurantResponseDto.builder()
                .restaurantId(restaurant.getId())
                .nameAddress(restaurant.getNameAddress())
                .city(restaurant.getCity())
                .name(restaurant.getName())
                .licenseDate(restaurant.getLicenseDate())
                .businessStatus(restaurant.getBusinessStatus())
                .type(restaurant.getType())
                .streetAddress(restaurant.getStreetAddress())
                .lotNumberAddress(restaurant.getLotNumberAddress())
                .postalCode(restaurant.getPostalCode())
                .longitude(restaurant.getLongitude())
                .latitude(restaurant.getLatitude())
                .rating(restaurant.getRating())
                .reviewList(reviewList)
                .build();
    }

}
