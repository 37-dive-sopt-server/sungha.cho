package org.sopt.exception;

public class InvalidGenderException extends RuntimeException {
    public InvalidGenderException(String input) {
        super("유효하지 않은 성별 입력입니다 (MALE 또는 FEMALE만 허용)");
    }
}