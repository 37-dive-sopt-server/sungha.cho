package org.sopt.exception.customexception;

import org.sopt.exception.constant.ErrorMessage;

public class AgeException extends RuntimeException {
    public AgeException() {
        super(ErrorMessage.UNDER_AGE);
    }
}
