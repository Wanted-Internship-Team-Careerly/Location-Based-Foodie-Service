package com.locationbasedfoodieservice.review.controller;

import com.locationbasedfoodieservice.common.config.auth.LoginMember;
import com.locationbasedfoodieservice.review.dto.request.ReviewRequestDto;
import com.locationbasedfoodieservice.review.dto.response.ReviewResponseDto;
import com.locationbasedfoodieservice.review.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@RequestBody @Valid ReviewRequestDto request,
            @AuthenticationPrincipal LoginMember loginMember) {

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.createReview(request, loginMember));
    }

}
