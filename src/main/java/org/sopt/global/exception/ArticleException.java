package org.sopt.global.exception;

import org.sopt.global.exception.constant.ErrorCode;

public class ArticleException extends BusinessException {
    public ArticleException(ErrorCode errorCode) {
        super(errorCode);
    }
}
