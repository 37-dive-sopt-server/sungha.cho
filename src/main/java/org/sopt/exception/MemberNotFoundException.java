package org.sopt.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long id) {
        super(ErrorMessage.MEMBER_NOT_FOUND + " (ID: " + id + ")");
    }
}