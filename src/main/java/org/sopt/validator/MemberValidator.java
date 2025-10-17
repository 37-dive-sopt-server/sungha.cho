package org.sopt.validator;

import org.sopt.repository.MemberRepository;

import java.time.LocalDate;

public class MemberValidator {

    private final NameValidator nameValidator;
    private final EmailValidator emailValidator;
    private final AgeValidator ageValidator;
    private final GenderValidator genderValidator;

    public MemberValidator() {
        this.nameValidator = new NameValidator();
        this.emailValidator = new EmailValidator();
        this.ageValidator = new AgeValidator();
        this.genderValidator = new GenderValidator();
    }

    public void validate(MemberRepository repo, String name, String email, LocalDate birth, String genderInput) {
        nameValidator.validateName(name);
        emailValidator.checkDuplicateEmail(repo, email);
        ageValidator.checkAdult(birth);
        genderValidator.validateGender(genderInput);
    }
}
