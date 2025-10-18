package org.sopt.exception.customexception;

import org.sopt.exception.constant.ErrorMessage;

public class InvalidNameException extends RuntimeException {
    public InvalidNameException() {
        super(ErrorMessage.INVALID_NAME);
    }
}
