package com.locationbasedfoodieservice.review.dto.response;

import com.locationbasedfoodieservice.review.entity.Review;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ReviewResponseDto {
    private long reviewId;
    private String content;
    private int score;


    public static ReviewResponseDto from(Review review) {
        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .score(review.getScore())
                .build();
    }

    public static List<ReviewResponseDto> from(List<Review> reviews) {
        return reviews.stream()
                .map(review -> ReviewResponseDto.builder()
                        .reviewId(review.getId())
                        .content(review.getContent())
                        .score(review.getScore())
                        .build())
                .collect(Collectors.toList());
    }

}
