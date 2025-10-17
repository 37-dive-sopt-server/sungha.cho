package org.sopt.validator;

import org.sopt.repository.MemberRepository;
import java.time.LocalDate;

public class MemberValidator {

    private MemberValidator() {}

    public static void validate(String name, int age, String genderInput) {
        NameValidator.validateName(name);
        AgeValidator.checkAdult(age);
        GenderValidator.validateGender(genderInput);
    }
}
