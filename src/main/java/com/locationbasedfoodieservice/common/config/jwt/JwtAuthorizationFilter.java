package com.locationbasedfoodieservice.common.config.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.locationbasedfoodieservice.common.config.auth.LoginMember;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 모든 주소에서 동작함 (토큰 검증)
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws
		IOException, ServletException {
		if (isHeaderVerify(request, response)) {
			// 토큰이 존재함
			String token = request.getHeader(JwtVO.HEADER).replace(JwtVO.TOKEN_PREFIX, "");
			LoginMember loginMember = JwtProcess.verify(token);

			// 임시 세션 (UserDetails 타입 or username)
			Authentication authentication =
				new UsernamePasswordAuthenticationToken(loginMember, null, loginMember.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		chain.doFilter(request, response);
	}

	private boolean isHeaderVerify(HttpServletRequest request, HttpServletResponse response) {
		String header = request.getHeader(JwtVO.HEADER);
		return header != null && header.startsWith(JwtVO.TOKEN_PREFIX);
	}
}
