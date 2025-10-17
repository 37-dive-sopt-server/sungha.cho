package org.sopt.domain;

import org.sopt.exception.customexception.InvalidGenderException;

public enum Gender {
    MALE, FEMALE;

    public static Gender fromString(String input) {
        if (input == null) {
            throw new InvalidGenderException(" ");
        }
        try {
            return Gender.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidGenderException(input);
        }
    }
}