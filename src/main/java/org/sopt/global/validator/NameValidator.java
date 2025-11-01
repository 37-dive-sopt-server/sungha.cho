package org.sopt.global.validator;

import org.sopt.global.exception.MemberException;
import org.sopt.global.exception.constant.ErrorCode;

public final class NameValidator {
    private NameValidator() {}
    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new MemberException(ErrorCode.INVALID_NAME);
        }
    }
}
