package com.locationbasedfoodieservice.member.service;

import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.locationbasedfoodieservice.common.config.auth.LoginMember;
import com.locationbasedfoodieservice.common.error.CustomErrorCode;
import com.locationbasedfoodieservice.common.exception.CustomException;
import com.locationbasedfoodieservice.member.dto.MemberResponseDto;
import com.locationbasedfoodieservice.member.dto.MemberSignupRequestDto;
import com.locationbasedfoodieservice.member.dto.MemberUpdateRequestDto;
import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	public void signup(MemberSignupRequestDto requestDto) {
		if (memberRepository.existsByAccount(requestDto.getAccount())) {
			throw new CustomException(CustomErrorCode.USER_ALREADY_EXIST);
		}

		memberRepository.save(requestDto.toEntity(passwordEncoder));
	}

	@Transactional
	public void update(Long memberId, MemberUpdateRequestDto requestDto, LoginMember loginMember) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

		if (!Objects.equals(member.getId(), loginMember.getMember().getId())) {
			throw new CustomException(CustomErrorCode.TOKEN_USER_MISMATCH);
		}

		member.update(requestDto.getLatitude(), requestDto.getLongitude(), requestDto.getIsSuggestion());
	}

	@Transactional(readOnly = true)
	public MemberResponseDto get(Long memberId, LoginMember loginMember) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new CustomException(CustomErrorCode.USER_NOT_FOUND));

		if (!Objects.equals(member.getId(), loginMember.getMember().getId())) {
			throw new CustomException(CustomErrorCode.TOKEN_USER_MISMATCH);
		}

		return MemberResponseDto.fromEntity(member);
	}
}
