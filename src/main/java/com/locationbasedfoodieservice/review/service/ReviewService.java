package com.locationbasedfoodieservice.review.service;

import com.locationbasedfoodieservice.common.config.auth.LoginMember;
import com.locationbasedfoodieservice.common.error.CustomErrorCode;
import com.locationbasedfoodieservice.common.exception.CustomException;
import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.member.repository.MemberRepository;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.restaurant.repository.RestaurantRepository;
import com.locationbasedfoodieservice.review.dto.request.ReviewRequestDto;
import com.locationbasedfoodieservice.review.dto.response.ReviewResponseDto;
import com.locationbasedfoodieservice.review.entity.Review;
import com.locationbasedfoodieservice.review.repository.ReviewRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;

    // member, restaurant 는 이미 생성되었다고 가정
    public ReviewResponseDto createReview(ReviewRequestDto request, LoginMember loginMember) {

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

        if (!Objects.equals(member.getId(), loginMember.getMember().getId())) {
            throw new CustomException(CustomErrorCode.TOKEN_USER_MISMATCH);
        }

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new CustomException(CustomErrorCode.RESTAURANT_NOT_FOUND));

        Review savedReview = reviewRepository.save(request.toEntity(member, restaurant));

        int totalReviewNum = reviewRepository.findCountByRestaurantId(request.getRestaurantId());
        int reviewScore = request.getScore();

        checkAndUpdateRating(restaurant, totalReviewNum, reviewScore);

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

    private void checkAndUpdateRating(Restaurant restaurant, int totalReviewNum, int reviewScore) {

        if (restaurant.getRating() != null) {
            double avg = restaurant.getRating();
            this.updateRestaurantRating(restaurant, totalReviewNum, reviewScore, avg);
        } else {
            restaurant.updateRating(reviewScore);
        }
    }

}
