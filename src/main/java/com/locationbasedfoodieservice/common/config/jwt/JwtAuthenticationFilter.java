package com.locationbasedfoodieservice.common.config.jwt;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationbasedfoodieservice.common.config.auth.LoginMember;
import com.locationbasedfoodieservice.member.dto.MemberLoginRequestDto;
import com.locationbasedfoodieservice.util.CustomResponseUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		setFilterProcessesUrl("/api/members/login");
		this.authenticationManager = authenticationManager;
	}

	// Post: /api/members/login
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
		AuthenticationException {

		try {
			ObjectMapper om = new ObjectMapper();
			MemberLoginRequestDto loginRequestDto = om.readValue(request.getInputStream(), MemberLoginRequestDto.class);

			// 강제 로그인
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				loginRequestDto.getAccount(), loginRequestDto.getPassword());

			// userDetailsService의 loadUserByUsername 호출
			// JWT를 쓴다 하더라도, 컨트롤러 진입하면 시큐리티의 권한체크, 인증체크의 도움을 받을 수 있게 세션을 만든다.
			// 이 세션이 유효기간은 request하고, response하면 끝
			return authenticationManager.authenticate(authenticationToken);
		} catch (Exception e) {
			// unsuccessfulAuthentication 호출함
			throw new InternalAuthenticationServiceException(e.getMessage());
		}
	}

	// 로그인 실패
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException failed) throws IOException, ServletException {
		CustomResponseUtil.fail(response, "로그인 실패", HttpStatus.UNAUTHORIZED);
	}

	// return authentication 잘 작동하면 successfulAuthentication 메서드 호출
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
		FilterChain chain, Authentication authResult) throws IOException, ServletException {

		LoginMember loginMember = (LoginMember)authResult.getPrincipal();
		String jwtToken = JwtProcess.create(loginMember);

		response.addHeader(JwtVO.HEADER, jwtToken);
		CustomResponseUtil.success(response);
	}
}
