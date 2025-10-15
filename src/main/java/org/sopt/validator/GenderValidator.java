package org.sopt.validator;

import org.sopt.domain.Gender;
import org.sopt.exception.InvalidGenderException;

public class GenderValidator {

    public void validateGender(String input) {
        if (input == null ||
                !(input.equalsIgnoreCase("MALE") || input.equalsIgnoreCase("FEMALE"))) {
            throw new InvalidGenderException(input);
        }
    }
}