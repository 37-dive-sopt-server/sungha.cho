package org.sopt.global.validator;

public class MemberValidator {

    private MemberValidator() {}

    public static void validate(String name, int age) {
        NameValidator.validateName(name);
        AgeValidator.checkAdult(age);
    }
}
