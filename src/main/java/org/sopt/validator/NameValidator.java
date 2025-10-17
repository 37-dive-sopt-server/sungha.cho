package org.sopt.validator;

import org.sopt.exception.InvalidNameException;

public class NameValidator {
    public void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException();
        }
    }
}