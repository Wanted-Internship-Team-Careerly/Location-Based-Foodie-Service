package com.locationbasedfoodieservice.review.dto.request;

import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.review.entity.Review;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewRequestDto {
    @NotNull
    private int score;

    private String content;

    @NotNull
    private long memberId;

    @NotNull
    private long restaurantId;

    public Review toEntity(Member member, Restaurant restaurant) {
        return Review.builder()
                .score(score)
                .content(content)
                .member(member)
                .restaurant(restaurant)
                .build();
    }
}
