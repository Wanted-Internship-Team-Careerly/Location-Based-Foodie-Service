package com.locationbasedfoodieservice.member.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.locationbasedfoodieservice.common.error.CustomErrorCode;
import com.locationbasedfoodieservice.common.exception.CustomException;
import com.locationbasedfoodieservice.member.dto.MemberSignupRequestDto;
import com.locationbasedfoodieservice.member.dto.MemberSignupResponseDto;
import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public MemberSignupResponseDto signup(MemberSignupRequestDto requestDto) {
		if (memberRepository.existsByAccount(requestDto.getAccount())) {
			throw new CustomException(CustomErrorCode.USER_ALREADY_EXIST);
		}

		Member member = memberRepository.save(requestDto.toEntity(passwordEncoder));
		return MemberSignupResponseDto.fromEntity(member);
	}
}
