package com.locationbasedfoodieservice.review.dto.request;

import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.review.entity.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewRequestDto {
    @NotNull(message = "평점은 공백일 수 없습니다")
    @Min(1)
    @Max(5)
    private Integer score;

    private String content;

    @NotNull
    private Long memberId;

    @NotNull
    private Long restaurantId;

    public Review toEntity(Member member, Restaurant restaurant) {
        return Review.builder()
                .score(score)
                .content(content)
                .member(member)
                .restaurant(restaurant)
                .build();
    }
}
