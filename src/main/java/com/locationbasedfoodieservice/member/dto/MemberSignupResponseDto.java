package com.locationbasedfoodieservice.member.dto;

import java.time.LocalDateTime;

import com.locationbasedfoodieservice.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MemberSignupResponseDto {

	private String account;
	private LocalDateTime createdAt;

	public static MemberSignupResponseDto fromEntity(Member member) {
		return MemberSignupResponseDto.builder()
			.account(member.getAccount())
			.createdAt(member.getCreatedAt())
			.build();
	}
}
