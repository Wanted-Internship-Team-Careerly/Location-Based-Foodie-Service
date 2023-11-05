package com.locationbasedfoodieservice.review.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.member.repository.MemberRepository;
import com.locationbasedfoodieservice.restaurant.entity.Restaurant;
import com.locationbasedfoodieservice.restaurant.repository.RestaurantRepository;
import com.locationbasedfoodieservice.review.dto.request.ReviewRequestDto;
import com.locationbasedfoodieservice.review.entity.Review;
import com.locationbasedfoodieservice.review.repository.ReviewRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ReviewControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private EntityManager em;


    @AfterEach
    void tearDown() {

    }


    @Transactional
    @Test
    void 리뷰_생성() throws Exception {
        Member member = Member.builder()
                .account("account")
                .password("pw")
                .latitude(1.1)
                .longitude(1.1)
                .isSuggestion(true)
                .build();
        Member savedMember = memberRepository.save(member);
        System.out.println("savedMemberId" + savedMember.getId());
        System.out.println("[검증] Review Controller memberSave 1");

        Restaurant restaurant = Restaurant.builder()
                .name("name")
                .nameAddress("address")
                .businessStatus("bs")
                .city("city")
                .licenseDate("date")
                .build();
        restaurantRepository.save(restaurant);

        Review review = Review.builder()
                .score(3)
                .content("내용")
                .member(member)
                .restaurant(restaurant)
                .build();
        reviewRepository.save(review);

        em.clear();

        // given
        ReviewRequestDto reviewRequestDto = new ReviewRequestDto(3, "내용", 4L, 1L);

        String requestBody = om.writeValueAsString(reviewRequestDto);

        System.out.println("[검증] Review Controller memberSelect 2");
        // when
        ResultActions resultActions = mvc.perform(post("/api/reviews")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        resultActions.andExpect(status().isCreated());
        resultActions.andExpect(jsonPath("$.content").exists());
        resultActions.andExpect(jsonPath("$.score").exists());
        resultActions.andDo(print());
    }
}
