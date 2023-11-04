package com.locationbasedfoodieservice.common.config.auth;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.locationbasedfoodieservice.member.entity.Member;
import com.locationbasedfoodieservice.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService implements UserDetailsService {

	private final MemberRepository memberRepository;

	// 시큐리티로 로그인 될 때, 시큐리티가 loadUserByUsername() 실행해서 account를 체크
	// 없으면 오류
	// 있으면 정상적으로 시큐리티 컨텍스트 내부 세션에 로그인된 세션이 만들어진다.
	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		Member member = memberRepository.findByAccount(account).orElseThrow(
			() -> new InternalAuthenticationServiceException("인증 실패")
		);

		return new LoginMember(member);
	}
}
