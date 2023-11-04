package com.locationbasedfoodieservice.member.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberUpdateRequestDto {

	private Double latitude;
	private Double longitude;
	private Boolean isSuggestion;
}
