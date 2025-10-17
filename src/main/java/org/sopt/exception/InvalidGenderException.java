package org.sopt.exception;

public class InvalidGenderException extends RuntimeException {
    public InvalidGenderException(String input) {
        super(input + ": " + ErrorMessage.INVALID_GENDER);
    }
}