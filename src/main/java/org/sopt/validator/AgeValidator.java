package org.sopt.validator;

import org.sopt.exception.customexception.AgeException;

public final class AgeValidator {
    private AgeValidator() {}

    private static final int MIN_AGE = 20;

    public static void checkAdult(int age) {
        if (age < MIN_AGE) {
            throw new AgeException();
        }
    }
}