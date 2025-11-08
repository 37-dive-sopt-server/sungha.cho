package org.sopt.member.domain;

import org.sopt.global.exception.BusinessException;
import static org.sopt.global.exception.constant.ErrorCode.INVALID_GENDER;

public enum Gender {
    MALE, FEMALE;

    public static Gender fromString(String input) {
        if (input == null) {
            throw new BusinessException(INVALID_GENDER);
        }
        try {
            return Gender.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException(INVALID_GENDER);
        }
    }
}