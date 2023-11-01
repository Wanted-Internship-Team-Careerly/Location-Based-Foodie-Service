package com.locationbasedfoodieservice.member.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationbasedfoodieservice.member.dto.MemberSignupRequestDto;
import com.locationbasedfoodieservice.member.dto.MemberSignupResponseDto;
import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.member.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

	@InjectMocks
	private MemberService memberService;

	@Mock
	private MemberRepository memberRepository;

	@Spy
	private BCryptPasswordEncoder passwordEncoder;

	@Spy
	private ObjectMapper objectMapper;

	@Test
	void 회원가입_test() throws Exception {
		// given
		MemberSignupRequestDto requestDto = new MemberSignupRequestDto();
		requestDto.setAccount("test");
		requestDto.setPassword("1234");
		requestDto.setLatitude(11.111);
		requestDto.setLongitude(22.222);
		requestDto.setSuggestion(true);

		// stub 1
		when(memberRepository.existsByAccount(any())).thenReturn(false);

		// stub 2
		Member member = Member.builder()
			.id(1L)
			.account("test")
			.password("1234")
			.latitude(11.111)
			.longitude(22.222)
			.isSuggestion(true)
			.build();

		when(memberRepository.save(any())).thenReturn(member);

		// when
		MemberSignupResponseDto responseDto = memberService.signup(requestDto);

		// then
		String responseBody = objectMapper.writeValueAsString(responseDto);
		System.out.println("테스트: " + responseBody);

		assertThat(responseDto.getAccount()).isEqualTo("test");
	}
}