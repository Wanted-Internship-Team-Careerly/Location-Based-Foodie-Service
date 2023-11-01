package com.locationbasedfoodieservice.member.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.locationbasedfoodieservice.member.entity.Member;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberSignupRequestDto {

	@NotBlank(message = "계정명을 입력해주시길 바랍니다.")
	private String account;

	@NotBlank(message = "패스워드를 입력해주시길 바랍니다.")
	private String password;

	private double latitude;
	private double longitude;
	private boolean isSuggestion;

	public Member toEntity(BCryptPasswordEncoder passwordEncoder) {
		return Member.builder()
			.account(account)
			.password(passwordEncoder.encode(password))
			.latitude(latitude)
			.longitude(longitude)
			.isSuggestion(isSuggestion)
			.build();
	}
}
