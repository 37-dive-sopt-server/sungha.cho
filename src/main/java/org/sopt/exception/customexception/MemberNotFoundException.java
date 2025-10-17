package org.sopt.exception.customexception;

import org.sopt.exception.constant.ErrorMessage;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long id) {
        super(ErrorMessage.MEMBER_NOT_FOUND + " (ID: " + id + ")");
    }
}