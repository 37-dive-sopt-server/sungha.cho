package org.sopt.global.validator;

import org.sopt.global.exception.constant.ErrorCode;
import org.sopt.global.exception.BusinessException;

public final class NameValidator {
    private NameValidator() {}
    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new BusinessException(ErrorCode.INVALID_NAME);
        }
    }
}
