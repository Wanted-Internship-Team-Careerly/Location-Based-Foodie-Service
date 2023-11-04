package com.locationbasedfoodieservice.review.dto.response;

import com.locationbasedfoodieservice.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Builder
public class ReviewResponseDto {
    private String content;
    private int score;


    public static ReviewResponseDto from(Review review) {
        return ReviewResponseDto.builder()
            .content(review.getContent())
            .score(review.getScore())
            .build();
    }

}

