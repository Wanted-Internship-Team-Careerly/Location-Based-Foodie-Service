package com.locationbasedfoodieservice.common.config.jwt;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.locationbasedfoodieservice.common.config.auth.LoginMember;
import com.locationbasedfoodieservice.member.entity.Member;

public class JwtProcess {

	// 토큰 생성
	public static String create(LoginMember loginMember) {
		String jwtToken = JWT.create()
			.withSubject("foodie")
			.withExpiresAt(new Date(System.currentTimeMillis() + JwtVO.EXPIRATION_TIME))
			.withClaim("id", loginMember.getMember().getId())
			.withClaim("role", "MEMBER")
			.sign(Algorithm.HMAC512(JwtVO.SECRET_KEY));

		return JwtVO.TOKEN_PREFIX + jwtToken;
	}

	// 토큰 검증(return 되는 LoginMember 객체를 강제로 시큐리티 세션에 직접 주입할 예정)
	public static LoginMember verify(String token) {
		DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JwtVO.SECRET_KEY)).build().verify(token);
		Long id = decodedJWT.getClaim("id").asLong();

		Member member = Member.builder()
			.id(id)
			.build();

		return new LoginMember(member);
	}
}
