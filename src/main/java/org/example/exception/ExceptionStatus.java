package org.example.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionStatus {
    NOT_FOUND("요청하신 데이터가 존재하지 않습니다."),
    NO_PERMISSION("권한이 없습니다.", FORBIDDEN),
    EXIST_LOGIN_ID("이미 존재하는 아이디입니다.");

    private final String message;
    private final HttpStatus httpStatus;

    ExceptionStatus(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    ExceptionStatus(String message) {
        this(message, BAD_REQUEST);
    }
}
