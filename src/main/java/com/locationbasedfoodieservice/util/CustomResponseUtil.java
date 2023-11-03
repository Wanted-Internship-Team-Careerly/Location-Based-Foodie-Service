package com.locationbasedfoodieservice.util;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationbasedfoodieservice.common.dto.ApiResponseDto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomResponseUtil {

	public static void success(HttpServletResponse response) {
		try {
			ObjectMapper om = new ObjectMapper();
			ApiResponseDto responseDto = new ApiResponseDto(HttpStatus.OK.value(), "로그인 성공");
			String responseBody = om.writeValueAsString(responseDto);

			response.setContentType("application/json; charset=utf-8");
			response.setStatus(HttpStatus.OK.value());
			response.getWriter().print(responseBody);
		} catch (Exception e) {
			log.error("서버 파싱 에러");
		}
	}

	public static void fail(HttpServletResponse response, String msg, HttpStatus httpStatus) {
		try {
			ObjectMapper om = new ObjectMapper();
			ApiResponseDto responseDto = new ApiResponseDto(HttpStatus.UNAUTHORIZED.value(), msg);
			String responseBody = om.writeValueAsString(responseDto);

			response.setContentType("application/json; charset=utf-8");
			response.setStatus(httpStatus.value());
			response.getWriter().print(responseBody);
		} catch (Exception e) {
			log.error("서버 파싱 에러");
		}
	}
}
