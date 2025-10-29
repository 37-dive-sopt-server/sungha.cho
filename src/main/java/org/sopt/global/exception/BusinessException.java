package org.sopt.global.exception;

import org.sopt.global.exception.constant.ErrorCode;

import java.text.MessageFormat;

public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode, Object... args) {
        super(format(errorCode.messageTemplate, args));
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    private static String format(String template, Object... args) {
        return (args == null || args.length == 0) ? template : MessageFormat.format(template, args);
    }
}
