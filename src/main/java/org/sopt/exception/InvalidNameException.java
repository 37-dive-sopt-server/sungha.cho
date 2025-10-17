package org.sopt.exception;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException() {
        super(ErrorMessage.INVALID_NAME);
    }
}
