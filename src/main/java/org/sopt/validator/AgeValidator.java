package org.sopt.validator;

import org.sopt.exception.AgeException;

import java.time.LocalDate;
import java.time.Period;

public final class AgeValidator {
    private AgeValidator() {}
    public static void checkAdult(LocalDate birth) {
        int age = Period.between(birth, LocalDate.now()).getYears();
        if (age < 20) {
            throw new AgeException();
        }
    }
}
