package org.sopt.validator;

import org.sopt.exception.AgeException;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator {

    public void checkAdult(LocalDate birth) {
        int age = calculateAge(birth);
        if (age < 20) {
            throw new AgeException();
        }
    }

    private int calculateAge(LocalDate birth) {
        LocalDate today = LocalDate.now();
        return Period.between(birth, today).getYears();
    }
}
