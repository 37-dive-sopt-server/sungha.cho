package org.sopt.validator;

import org.sopt.repository.MemberRepository;
import java.time.LocalDate;

public class MemberValidator {

    private MemberValidator() {}

    public static void validate(MemberRepository repo, String name, String email, LocalDate birth, String genderInput) {
        NameValidator.validateName(name);
        EmailValidator.checkDuplicateEmail(repo, email);
        AgeValidator.checkAdult(birth);
        GenderValidator.validateGender(genderInput);
    }
}
