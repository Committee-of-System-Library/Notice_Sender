package com.knu.noticesender.core.exception;

import com.knu.noticesender.core.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler({KnuException.class})
    public ApiResponse<?> knuExceptionHandler(KnuException e, HttpServletResponse response) {
        response.setStatus(e.getResultCode().getHttpStatus().value());
        return ApiResponse.error(e);
    }

    @ExceptionHandler({RuntimeException.class})
    public ApiResponse<?> RuntimeHandler(RuntimeException e, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ApiResponse.error();
    }
}
