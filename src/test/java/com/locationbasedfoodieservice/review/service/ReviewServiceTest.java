package com.locationbasedfoodieservice.review.service;


import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.member.repository.MemberRepository;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.restaurant.repository.RestaurantRepository;
import com.locationbasedfoodieservice.review.dto.request.ReviewRequestDto;
import com.locationbasedfoodieservice.review.entity.Review;
import com.locationbasedfoodieservice.review.repository.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void setUp() {
        member = Member.builder()
                .id(1L)
                .account("account1")
                .password("pw")
                .latitude(1.1)
                .longitude(1.1)
                .isSuggestion(true)
                .build();
        memberRepository.saveAndFlush(member);

        restaurant = Restaurant.builder()
                .id(1L)
                .name("name")
                .nameAddress("address")
                .businessStatus("bs")
                .city("city")
                .licenseDate("date")
                .build();
        restaurantRepository.saveAndFlush(restaurant);

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
    }

    @Test
    void 리뷰_생성() {
        //given
        ReviewRequestDto reviewRequestDto = new ReviewRequestDto(3, "내용", 1L, 1L);


        //when
        reviewService.createReview(reviewRequestDto);

        //then
        Assertions.assertThat(review.getMember().getId()).isEqualTo(1L);
        Assertions.assertThat(review.getRestaurant().getId()).isEqualTo(1L);
        Assertions.assertThat(review.getScore()).isEqualTo(3);


    }

}