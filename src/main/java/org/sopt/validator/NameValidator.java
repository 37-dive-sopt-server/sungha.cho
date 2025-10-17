package org.sopt.validator;

import org.sopt.exception.InvalidNameException;

public final class NameValidator {
    private NameValidator() {}
    public static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException();
        }
    }
}