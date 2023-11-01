package com.locationbasedfoodieservice.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locationbasedfoodieservice.common.dto.ApiResponseDto;
import com.locationbasedfoodieservice.member.dto.MemberSignupRequestDto;
import com.locationbasedfoodieservice.member.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/signup")
	public ResponseEntity<ApiResponseDto> signup(@RequestBody @Valid MemberSignupRequestDto requestDto) {
		memberService.signup(requestDto);

		return ResponseEntity.ok().body(new ApiResponseDto(HttpStatus.CREATED.value(), "회원 가입 완료"));
	}
}
