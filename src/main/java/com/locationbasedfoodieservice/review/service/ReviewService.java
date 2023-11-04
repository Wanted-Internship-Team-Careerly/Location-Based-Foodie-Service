package com.locationbasedfoodieservice.review.service;

import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.review.dto.request.ReviewRequestDto;
import com.locationbasedfoodieservice.review.dto.response.ReviewResponseDto;
import com.locationbasedfoodieservice.review.entity.Review;
import com.locationbasedfoodieservice.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    // member, restaurant 는 이미 생성되었다고 가정
    public ReviewResponseDto createReview(ReviewRequestDto request) {

        Member member = Member.builder().account("account").password("password")
                .isSuggestion(true).latitude(1.0).latitude(1.0).build();
        Restaurant restaurant = Restaurant.builder().build();

        Review savedReview = reviewRepository.save(request.toEntity(member, restaurant));

        int totalReviewNum = reviewRepository.findCountByRestaurantId(request.getRestaurantId());
        int reviewScore = request.getScore();
        double avg = restaurant.getRating();

        updateRestaurantRating(restaurant, totalReviewNum, reviewScore, avg);

        return ReviewResponseDto.from(savedReview);
    }

    // todo
    // 평균 * 총 개수
    // 새점수 + 총점
    // 평균 계산
    // 리뷰 평점 업데이트
    private void updateRestaurantRating(Restaurant restaurant, int totalReviewNum,
            int reviewScore, double avg) {
        double calAvg = avg * totalReviewNum;
        double newCalAvg = calAvg + reviewScore;
        double resultAvg = newCalAvg / (totalReviewNum + 1);
        restaurant.updateRating(resultAvg);
    }

}
