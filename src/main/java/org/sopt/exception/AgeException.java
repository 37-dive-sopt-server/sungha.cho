package org.sopt.exception;

public class AgeException extends RuntimeException {
    public AgeException() {
        super(ErrorMessage.UNDER_AGE);
    }
}
