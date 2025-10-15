package org.sopt.validator;

import org.sopt.repository.MemberRepository;

import java.time.LocalDate;

public class MemberValidator {

    private final EmailValidator emailValidator;
    private final AgeValidator ageValidator;

    public MemberValidator() {
        this.emailValidator = new EmailValidator();
        this.ageValidator = new AgeValidator();
    }

    public void validate(MemberRepository repo, String email, LocalDate birth) {
        emailValidator.checkDuplicateEmail(repo, email);
        ageValidator.checkAdult(birth);
    }
}
