package com.knu.noticesender.core.response;

import com.knu.noticesender.core.exception.KnuException;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class ApiResponse<T> implements Serializable {

    private ResponseResult result;
    private T data;

    public ApiResponse(ResponseResult result, T data) {
        this.result = result;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse<>(new ResponseResult(ResultCode.SUCCESS), null);
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(new ResponseResult(ResultCode.SUCCESS), data);
    }

    public static <T> ApiResponse<T> error(KnuException e) {
        return new ApiResponse<>(new ResponseResult(e.getResultCode()), null);
    }

    public static <T> ApiResponse<T> error() {
        return new ApiResponse<>(new ResponseResult(ResultCode.INTERNAL_SERVER_ERROR), null);
    }

}
