package com.locationbasedfoodieservice.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.locationbasedfoodieservice.common.config.jwt.JwtAuthenticationFilter;
import com.locationbasedfoodieservice.common.config.jwt.JwtAuthorizationFilter;
import com.locationbasedfoodieservice.util.CustomResponseUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// JWT 필터 등록이 필요함
	public static class CustomerSecurityFilterManager extends
		AbstractHttpConfigurer<CustomerSecurityFilterManager, HttpSecurity> {

		@Override
		public void configure(HttpSecurity builder) throws Exception {
			AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
			builder.addFilter(new JwtAuthenticationFilter(authenticationManager));
			builder.addFilter(new JwtAuthorizationFilter(authenticationManager));
			super.configure(builder);
		}
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers(AbstractHttpConfigurer::disable); // iframe 허용 안함
		http.csrf(AbstractHttpConfigurer::disable); // enable이면 포스트맨 작동 안함
		http.cors(configurer -> configurationSource());

		// jSessionId를 서버쪽에서 관리 X
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// react, 앱으로 요청할 예정
		http.formLogin(AbstractHttpConfigurer::disable);

		// httpBasic은 브라우저가 팝업창을 이용해서 사용자 인증을 진행한다.
		http.httpBasic(AbstractHttpConfigurer::disable);

		// 필터 적용
		http.apply(new CustomerSecurityFilterManager());

		// 인증 실패
		http.exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
			httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) ->
				CustomResponseUtil.fail(response, "로그인을 진행해 주세요.", HttpStatus.UNAUTHORIZED))
		);

		http.exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
			httpSecurityExceptionHandlingConfigurer.accessDeniedHandler((request, response, accessDeniedException) ->
				CustomResponseUtil.fail(response, "권한이 없습니다.", HttpStatus.FORBIDDEN))
		);

		http.authorizeHttpRequests(
			authorizeHttpRequests -> authorizeHttpRequests
				.requestMatchers("/api/members/**").permitAll()
				.anyRequest().authenticated()
		);
		return http.build();
	}

	public void configurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*"); // GET, POST, PUT, DELETE (자바스크립트 요청 허용)
		configuration.addAllowedOriginPattern("*"); // 모든 IP 주소 허용
		configuration.setAllowCredentials(true); // 클라이언트에서 쿠키 요청 허용

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
	}
}
