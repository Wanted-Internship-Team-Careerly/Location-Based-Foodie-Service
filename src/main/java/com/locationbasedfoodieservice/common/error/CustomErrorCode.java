package com.locationbasedfoodieservice.common.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    // 예시 에러코드입니다.
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 사용자입니다."),
    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), "이미 존재하는 계정입니다."),
    TOKEN_USER_MISMATCH(HttpStatus.BAD_REQUEST.value(), "계정에 잘못된 접근입니다."),
    INVALID_SIGUNGU_EXCEPTION(HttpStatus.BAD_REQUEST.value(), "잘못된 시군구 요청입니다"),
    SIGUNGU_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 시군구입니다"),
    RESTAURANT_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 가게입니다.");

    private final int errorCode;
    private final String errorMessage;
}