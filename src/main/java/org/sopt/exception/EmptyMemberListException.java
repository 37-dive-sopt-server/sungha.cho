package org.sopt.exception;

public class EmptyMemberListException extends RuntimeException {
    public EmptyMemberListException() {
        super(ErrorMessage.EMPTY_MEMBER_LIST);
    }
}