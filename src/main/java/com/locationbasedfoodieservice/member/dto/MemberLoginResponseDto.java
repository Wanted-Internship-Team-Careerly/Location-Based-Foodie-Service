package com.locationbasedfoodieservice.member.dto;

import com.locationbasedfoodieservice.member.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberLoginResponseDto {

	private Long id;
	private String account;

	public MemberLoginResponseDto(Member member) {
		this.id = member.getId();
		this.account = member.getAccount();
	}
}
