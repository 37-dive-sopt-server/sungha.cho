package org.sopt.validator;

import org.sopt.exception.AgeException;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator {

    // 나이 검증 (20세 미만 가입 불가)
    public void checkAdult(LocalDate birth) {
        int age = calculateAge(birth);
        if (age < 20) {
            throw new AgeException(age);
        }
    }

    // 나이 계산 (현재 날짜 기준)
    private int calculateAge(LocalDate birth) {
        LocalDate today = LocalDate.now();
        return Period.between(birth, today).getYears();
    }
}
