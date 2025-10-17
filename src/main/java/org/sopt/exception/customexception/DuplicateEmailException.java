package org.sopt.exception.customexception;

import org.sopt.exception.constant.ErrorMessage;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super(ErrorMessage.DUPLICATE_EMAIL + email);
    }
}
