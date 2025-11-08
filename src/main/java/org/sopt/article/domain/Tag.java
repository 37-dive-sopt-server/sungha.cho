package org.sopt.article.domain;

import org.sopt.global.exception.BusinessException;
import static org.sopt.global.exception.constant.ErrorCode.INVALID_TAG;

public enum Tag {
    CS, DB, SPRING, ETC;

    public static Tag fromString(String input) {
        if (input == null) throw new BusinessException(INVALID_TAG);
        try {
            return Tag.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException(INVALID_TAG);
        }
    }
}
