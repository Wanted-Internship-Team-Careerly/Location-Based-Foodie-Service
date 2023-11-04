package com.locationbasedfoodieservice.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationbasedfoodieservice.member.dto.MemberSignupRequestDto;
import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.member.repository.MemberRepository;

import jakarta.persistence.EntityManager;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class MemberControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper om;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private EntityManager em;

	@BeforeEach
	void setUp() {
		Member member = Member.builder()
			.account("test")
			.password("1234")
			.latitude(12.123)
			.longitude(12.123)
			.isSuggestion(false)
			.build();
		memberRepository.save(member);
		em.clear();
	}

	@AfterEach
	void tearDown() {
		memberRepository.deleteAll();
	}

	@Test
	void 회원가입_성공_test() throws Exception {
		// given
		MemberSignupRequestDto requestDto = new MemberSignupRequestDto();
		requestDto.setAccount("hong");
		requestDto.setPassword("1234");
		requestDto.setLatitude(12.123);
		requestDto.setLongitude(12.123);

		String requestBody = om.writeValueAsString(requestDto);

		// when
		ResultActions resultActions = mvc.perform(post("/api/members/signup")
			.content(requestBody)
			.contentType(MediaType.APPLICATION_JSON)
		);

		// then
		resultActions.andExpect(status().isOk());
	}

	@Test
	void 회원가입_실패_test() throws Exception {
		// given
		MemberSignupRequestDto requestDto = new MemberSignupRequestDto();
		requestDto.setAccount("test");
		requestDto.setPassword("1234");
		requestDto.setLatitude(12.123);
		requestDto.setLongitude(12.123);

		String requestBody = om.writeValueAsString(requestDto);

		// when
		ResultActions resultActions = mvc.perform(post("/api/members/signup")
			.content(requestBody)
			.contentType(MediaType.APPLICATION_JSON)
		);

		// then
		resultActions.andExpect(status().isBadRequest());
	}
}