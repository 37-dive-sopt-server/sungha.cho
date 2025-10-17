package org.sopt.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(Long id) {
        super("⚠️ 해당 ID(" + id + ")의 회원을 찾을 수 없습니다.");
    }
}