package org.sopt.exception;

public class InvalidGenderException extends RuntimeException {
    public InvalidGenderException(String input) {
        super(ErrorMessage.INVALID_GENDER);
    }
}