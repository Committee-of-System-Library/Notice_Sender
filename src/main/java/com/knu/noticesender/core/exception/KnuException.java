package com.knu.noticesender.core.exception;

import com.knu.noticesender.core.response.ResultCode;
import lombok.Getter;

@Getter
public class KnuException extends RuntimeException{
    private ResultCode resultCode = ResultCode.INTERNAL_SERVER_ERROR;

    public KnuException(ResultCode resultCode) {
        super();
        this.resultCode = resultCode;
    }

    public KnuException() {
        super();
    }
}
