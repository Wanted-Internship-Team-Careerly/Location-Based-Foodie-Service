package com.locationbasedfoodieservice.review.service;


import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.member.repository.MemberRepository;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.restaurant.repository.RestaurantRepository;
import com.locationbasedfoodieservice.review.dto.request.ReviewRequestDto;
import com.locationbasedfoodieservice.review.dto.response.ReviewResponseDto;
import com.locationbasedfoodieservice.review.entity.Review;
import com.locationbasedfoodieservice.review.repository.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MemberRepository memberRepository;

    Member member;

    Restaurant restaurant;

    Review review;

    @BeforeEach
    void setUpMember() {
        member = Member.builder()
                .account("account")
                .password("pw")
                .latitude(1.1)
                .longitude(1.1)
                .isSuggestion(true)
                .build();
        memberRepository.save(member);

        restaurant = Restaurant.builder()
                .name("name")
                .nameAddress("address")
                .businessStatus("bs")
                .city("city")
                .licenseDate("date")
                .build();
        restaurantRepository.save(restaurant);

        review = Review.builder()
                .score(3)
                .content("내용")
                .member(member)
                .restaurant(restaurant)
                .build();
    }

    @AfterEach
    void tearDown() {
        reviewRepository.deleteAll();
        memberRepository.deleteAll();
        restaurantRepository.deleteAll();
    }

    @WithUserDetails(value = "account", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    void 리뷰_생성() {
        //given
        ReviewRequestDto reviewRequestDto = new ReviewRequestDto(3, "내용", 1L, 1L);


        //when
        ReviewResponseDto dto = reviewService.createReview(reviewRequestDto);

        //then
        Assertions.assertThat(dto.getScore()).isEqualTo(3);
        Assertions.assertThat(dto.getContent()).isEqualTo("내용");
    }

}