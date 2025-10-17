package org.sopt.validator;

import org.sopt.exception.InvalidGenderException;

public final class GenderValidator {
    private GenderValidator() {}
    public static void validateGender(String input) {
        if (input == null ||
                !(input.equalsIgnoreCase("MALE") || input.equalsIgnoreCase("FEMALE"))) {
            throw new InvalidGenderException(input);
        }
    }
}