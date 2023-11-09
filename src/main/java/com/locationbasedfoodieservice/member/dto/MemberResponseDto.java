package com.locationbasedfoodieservice.member.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.locationbasedfoodieservice.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MemberResponseDto {

	private Long id;
	private String account;
	private Double latitude;
	private Double longitude;
	private Boolean isSuggestion;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updatedAt;

	public static MemberResponseDto fromEntity(Member member) {
		return MemberResponseDto.builder()
			.id(member.getId())
			.account(member.getAccount())
			.latitude(member.getLatitude())
			.longitude(member.getLongitude())
			.isSuggestion(member.getIsSuggestion())
			.createdAt(member.getCreatedAt())
			.updatedAt(member.getModifiedAt())
			.build();
	}
}
