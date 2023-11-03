package com.locationbasedfoodieservice.review.service;

import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.review.dto.request.ReviewRequestDto;
import com.locationbasedfoodieservice.review.dto.response.ReviewResponseDto;
import com.locationbasedfoodieservice.review.entity.Review;
import com.locationbasedfoodieservice.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResponseDto createReview(ReviewRequestDto request) {

        Member member = Member.builder().account("account").password("password")
                .isSuggestion(true).latitude(1.0).latitude(1.0).build();
        Restaurant restaurant = Restaurant.builder().build();

        Review savedReview = reviewRepository.save(request.toEntity(member, restaurant));

        return ReviewResponseDto.from(savedReview);
    }
}
