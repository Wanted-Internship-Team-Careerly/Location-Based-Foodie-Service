package com.locationbasedfoodieservice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.locationbasedfoodieservice.common.dto.ApiResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

	@ExceptionHandler({CustomException.class})
	public ResponseEntity<ApiResponseDto> handlerCustomException(CustomException e) {
		ApiResponseDto restApiException =
			new ApiResponseDto(e.getErrorCode().getErrorCode(), e.getErrorCode().getErrorMessage());
		return ResponseEntity.status(restApiException.getStatusCode()).body(restApiException);
	}

	// validation에서 예외가 생겼을 때는 여기서 잡아줍니다.
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<ApiResponseDto> handlerValicationException(MethodArgumentNotValidException ex) {
		// Validation 예외처리
		StringBuilder errorMessage = new StringBuilder();
		for (FieldError fieldError : ex.getFieldErrors()) {
			log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
			errorMessage.append(fieldError.getField())
				.append(" 필드 : ")
				.append(fieldError.getDefaultMessage())
				.append(" ");
		}

		return ResponseEntity.badRequest()
			.body(new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), errorMessage.toString()));
	}
}