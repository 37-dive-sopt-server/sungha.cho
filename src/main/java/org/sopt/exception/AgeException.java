package org.sopt.exception;

public class AgeException extends RuntimeException {
    public AgeException(int age) {
        super("가입 불가: 만 20세 미만입니다.");
    }
}
