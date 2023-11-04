package com.locationbasedfoodieservice.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.locationbasedfoodieservice.common.config.auth.LoginMember;
import com.locationbasedfoodieservice.common.dto.ApiResponseDto;
import com.locationbasedfoodieservice.member.dto.MemberSignupRequestDto;
import com.locationbasedfoodieservice.member.dto.MemberUpdateRequestDto;
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

	@PutMapping("/{memberId}")
	public ResponseEntity<ApiResponseDto> update(
		@PathVariable Long memberId,
		@RequestBody MemberUpdateRequestDto requestDto,
		@AuthenticationPrincipal LoginMember loginMember) {
		memberService.update(memberId, requestDto, loginMember);

		return ResponseEntity.ok(new ApiResponseDto(HttpStatus.OK.value(), "회원 정보 업데이트"));
	}
}
