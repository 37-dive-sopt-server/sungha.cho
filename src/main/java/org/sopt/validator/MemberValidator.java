package org.sopt.validator;

public class MemberValidator {

    private MemberValidator() {}

    public static void validate(String name, int age, String genderInput) {
        NameValidator.validateName(name);
        AgeValidator.checkAdult(age);
        GenderValidator.validateGender(genderInput);
    }
}
