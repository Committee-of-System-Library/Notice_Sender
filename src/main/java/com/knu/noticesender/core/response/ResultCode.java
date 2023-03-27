package com.knu.noticesender.core.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ResultCode {

    /*
     status(3자리)_숫자(3자리)
     */
    SUCCESS(HttpStatus.OK, 200_001, "success"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500_001, "internal server error");
    private HttpStatus httpStatus;
    private int code;
    private String message;

    ResultCode(HttpStatus httpStatus, int code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
