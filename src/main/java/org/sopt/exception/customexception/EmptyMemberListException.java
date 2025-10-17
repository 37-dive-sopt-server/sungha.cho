package org.sopt.exception.customexception;

import org.sopt.exception.constant.ErrorMessage;

public class EmptyMemberListException extends RuntimeException {
    public EmptyMemberListException() {
        super(ErrorMessage.EMPTY_MEMBER_LIST);
    }
}