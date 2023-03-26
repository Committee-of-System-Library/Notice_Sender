package com.knu.noticesender.core.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ResultCode {
    SUCCESS(HttpStatus.OK, 200, "success"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "internal server error");
    private HttpStatus httpStatus;
    private int code;
    private String message;

    ResultCode(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
