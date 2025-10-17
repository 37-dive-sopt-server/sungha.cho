package org.sopt.exception;

public class InvalidGenderException extends RuntimeException {
    public InvalidGenderException(String input) {
        super("입력값 " + "'" + input + "'은(는) " + ErrorMessage.INVALID_GENDER);
    }
}