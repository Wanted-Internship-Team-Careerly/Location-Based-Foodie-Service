package com.locationbasedfoodieservice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.locationbasedfoodieservice.common.error.CustomErrorCode;

import lombok.Getter;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomException extends RuntimeException {

	private final CustomErrorCode errorCode;

	public CustomException(CustomErrorCode errorCode) {
		super(errorCode.getErrorMessage());
		this.errorCode = errorCode;
	}
}