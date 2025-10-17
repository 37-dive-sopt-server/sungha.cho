package org.sopt.validator;

import org.sopt.repository.MemberRepository;
import java.time.LocalDate;

public class MemberValidator {

    private MemberValidator() {}

    public static void validate(String name, LocalDate birth, String genderInput) {
        NameValidator.validateName(name);
        AgeValidator.checkAdult(birth);
        GenderValidator.validateGender(genderInput);
    }
}
