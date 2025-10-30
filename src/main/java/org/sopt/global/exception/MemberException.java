package org.sopt.global.exception;

import org.sopt.global.exception.constant.ErrorCode;

public class MemberException extends BusinessException {
    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}