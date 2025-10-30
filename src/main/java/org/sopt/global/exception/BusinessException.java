package org.sopt.global.exception;

import org.sopt.global.exception.constant.ErrorCode;

import java.text.MessageFormat;

public class  BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
