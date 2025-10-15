package org.sopt.validator;

import org.sopt.domain.Gender;
import org.sopt.repository.MemberRepository;

import java.time.LocalDate;

public class MemberValidator {

    private final EmailValidator emailValidator;
    private final AgeValidator ageValidator;
    private final GenderValidator genderValidator;

    public MemberValidator() {
        this.emailValidator = new EmailValidator();
        this.ageValidator = new AgeValidator();
        this.genderValidator = new GenderValidator();
    }

    public void validate(MemberRepository repo, String email, LocalDate birth, String genderInput) {
        emailValidator.checkDuplicateEmail(repo, email);
        ageValidator.checkAdult(birth);
        genderValidator.validateGender(genderInput);
    }
}
