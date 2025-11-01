package org.sopt.global.validator;

import org.sopt.global.exception.MemberException;
import org.sopt.global.exception.constant.ErrorCode;

public final class AgeValidator {
    private AgeValidator() {}

    private static final int MIN_AGE = 20;

    public static void checkAdult(int age) {
        if (age < MIN_AGE) {
            throw new MemberException(ErrorCode.UNDER_AGE);
        }
    }
}