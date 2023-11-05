package com.locationbasedfoodieservice.review.service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.locationbasedfoodieservice.common.config.auth.LoginMember;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.member.repository.MemberRepository;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.restaurant.repository.RestaurantRepository;
import com.locationbasedfoodieservice.review.dto.request.ReviewRequestDto;
import com.locationbasedfoodieservice.review.dto.response.ReviewResponseDto;
import com.locationbasedfoodieservice.review.entity.Review;
import com.locationbasedfoodieservice.review.repository.ReviewRepository;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private MemberRepository memberRepository;

    @Test
    void 리뷰_생성() {
        //given
        Member member = Member.builder()
                .id(1L)
                .account("account1")
                .password("pw")
                .latitude(1.1)
                .longitude(1.1)
                .isSuggestion(true)
                .build();

        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .name("name")
                .nameAddress("address")
                .businessStatus("bs")
                .city("city")
                .licenseDate("date")
                .build();

        Review review = Review.builder()
                .score(3)
                .content("내용")
                .member(member)
                .restaurant(restaurant)
                .build();

        ReviewRequestDto reviewRequestDto = new ReviewRequestDto(3, "내용", 1L, 1L);

        // stub 1
        when(memberRepository.findById(any())).thenReturn(Optional.of(member));

        // stub 2
        when(restaurantRepository.findById(any())).thenReturn(Optional.of(restaurant));

        // stub 3
        when(reviewRepository.save(any())).thenReturn(review);

        LoginMember loginMember = new LoginMember(member);

        //when
        ReviewResponseDto responseDto = reviewService.createReview(reviewRequestDto, loginMember);

        //then
        Assertions.assertThat(responseDto.getContent()).isEqualTo("내용");
        Assertions.assertThat(responseDto.getScore()).isEqualTo(3L);
    }

}