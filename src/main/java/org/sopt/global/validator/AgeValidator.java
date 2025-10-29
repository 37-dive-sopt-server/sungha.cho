package org.sopt.global.validator;

import org.sopt.global.exception.constant.ErrorCode;
import org.sopt.global.exception.BusinessException;

public final class AgeValidator {
    private AgeValidator() {}

    private static final int MIN_AGE = 20;

    public static void checkAdult(int age) {
        if (age < MIN_AGE) {
            throw new BusinessException(ErrorCode.UNDER_AGE);
        }
    }
}