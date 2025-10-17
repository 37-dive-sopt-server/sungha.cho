package org.sopt.exception;

public class EmptyMemberListException extends RuntimeException {
    public EmptyMemberListException() {
        super("ℹ️ 등록된 회원이 없습니다.");
    }
}