package com.locationbasedfoodieservice.review.dto.request;

import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewRequestDto {
    private int score;

    private String content;

    private Long memberId;

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
