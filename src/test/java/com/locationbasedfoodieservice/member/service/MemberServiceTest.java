package com.locationbasedfoodieservice.member.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.locationbasedfoodieservice.common.config.auth.LoginMember;
import com.locationbasedfoodieservice.common.error.CustomErrorCode;
import com.locationbasedfoodieservice.common.exception.CustomException;
import com.locationbasedfoodieservice.member.dto.MemberResponseDto;
import com.locationbasedfoodieservice.member.dto.MemberSignupRequestDto;
import com.locationbasedfoodieservice.member.dto.MemberUpdateRequestDto;
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
		memberService.signup(requestDto);

		// then
		assertThat(member.getAccount()).isEqualTo("test");
	}

	@Test
	void 업데이트_성공_test() throws Exception {
		// given
		MemberUpdateRequestDto requestDto = new MemberUpdateRequestDto();
		requestDto.setLatitude(22.222);
		requestDto.setLongitude(22.222);
		requestDto.setIsSuggestion(true);

		// stub 1
		Member member = Member.builder()
			.id(1L)
			.account("test")
			.password("1234")
			.latitude(11.111)
			.longitude(11.111)
			.isSuggestion(false)
			.build();
		when(memberRepository.findById(any())).thenReturn(Optional.of(member));

		// when
		LoginMember loginMember = new LoginMember(member);
		memberService.update(member.getId(), requestDto, loginMember);

		// then
		assertThat(member.getLatitude()).isEqualTo(22.222);
		assertThat(member.getLongitude()).isEqualTo(22.222);
		assertThat(member.getIsSuggestion()).isEqualTo(true);
	}

	@Test
	void 업데이트_실패_찾을_수_없는_회원_번호_test() throws Exception {
		// given
		MemberUpdateRequestDto requestDto = new MemberUpdateRequestDto();
		requestDto.setLatitude(22.222);
		requestDto.setLongitude(22.222);
		requestDto.setIsSuggestion(true);

		// stub 1
		Member member = Member.builder()
			.account("test")
			.password("1234")
			.latitude(11.111)
			.longitude(11.111)
			.isSuggestion(false)
			.build();
		when(memberRepository.findById(any())).thenReturn(Optional.empty());

		// when
		LoginMember loginMember = new LoginMember(member);
		CustomException customException = assertThrows(CustomException.class,
			() -> memberService.update(member.getId(), requestDto, loginMember));

		// then
		assertEquals(CustomErrorCode.USER_NOT_FOUND.getErrorMessage(), customException.getMessage());
	}

	@Test
	void 업데이트_실패_토큰_계정이_일치하지_않을때_test() throws Exception {
		// given
		MemberUpdateRequestDto requestDto = new MemberUpdateRequestDto();
		requestDto.setLatitude(22.222);
		requestDto.setLongitude(22.222);
		requestDto.setIsSuggestion(true);

		Member hong = Member.builder()
			.id(1L)
			.account("hong")
			.password("1234")
			.latitude(11.111)
			.longitude(11.111)
			.isSuggestion(false)
			.build();
		LoginMember loginMember = new LoginMember(hong);

		// stub 1
		Member kim = Member.builder()
			.id(2L)
			.account("kim")
			.password("1234")
			.latitude(11.111)
			.longitude(11.111)
			.isSuggestion(false)
			.build();
		when(memberRepository.findById(any())).thenReturn(Optional.of(kim));

		// when
		CustomException customException = assertThrows(CustomException.class,
			() -> memberService.update(kim.getId(), requestDto, loginMember));

		// then
		assertEquals(CustomErrorCode.TOKEN_USER_MISMATCH.getErrorMessage(), customException.getMessage());
	}

	@Test
	void 회원_정보_조회_test() throws Exception {
		// given
		Member hong = Member.builder()
			.id(1L)
			.account("hong")
			.password("1234")
			.latitude(11.111)
			.longitude(11.111)
			.isSuggestion(false)
			.build();
		LoginMember loginMember = new LoginMember(hong);

		// stub 1
		when(memberRepository.findById(any())).thenReturn(Optional.of(hong));

		// when
		MemberResponseDto responseDto = memberService.get(1L, loginMember);

		// then
		assertThat(responseDto.getId()).isEqualTo(1L);
		assertThat(responseDto.getAccount()).isEqualTo("hong");
		assertThat(responseDto.getLatitude()).isEqualTo(11.111);
		assertThat(responseDto.getLongitude()).isEqualTo(11.111);
		assertThat(responseDto.getIsSuggestion()).isFalse();
	}
}