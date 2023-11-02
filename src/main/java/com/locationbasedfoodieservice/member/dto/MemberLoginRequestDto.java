package com.locationbasedfoodieservice.member.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberLoginRequestDto {

	private String account;
	private String password;
}
