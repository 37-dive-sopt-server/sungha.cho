package org.sopt.validator;

import org.sopt.exception.AgeException;
import org.sopt.repository.MemberRepository;
import org.sopt.exception.DuplicateEmailException;

import java.time.LocalDate;
import java.time.Period;

public class MemberValidator {

    // 이메일 중복 확인
    public void checkDuplicateEmail(MemberRepository repo, String email) {
        repo.findByEmail(email).ifPresent(m -> {
            throw new DuplicateEmailException(email);
        });
    }

    // 나이 검증 (20세 미만 가입 불가)
    public void checkAdult(LocalDate birth) { // 만 20세 미만이면 예외 처리
        int age = calculateAge(birth);
        if (age < 20) {
            throw new AgeException(age);
        }
    }

    // 나이 계산 (현재 날짜 기준)
    private int calculateAge(LocalDate birth) {
        LocalDate today = LocalDate.now();
        return Period.between(birth, today).getYears(); // 생년월일 기준으로 만 나이 계산
    }
}
