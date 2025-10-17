package org.sopt.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super(ErrorMessage.DUPLICATE_EMAIL + email);
    }
}
