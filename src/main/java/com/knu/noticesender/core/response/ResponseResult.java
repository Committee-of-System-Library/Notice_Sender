package com.knu.noticesender.core.response;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ResponseResult implements Serializable {
    int code;
    String message;

    public ResponseResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}


