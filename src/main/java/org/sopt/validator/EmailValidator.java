package org.sopt.validator;

import org.sopt.exception.DuplicateEmailException;
import org.sopt.repository.MemberRepository;

public final class EmailValidator {
    private EmailValidator() {}
    public static void checkDuplicateEmail(MemberRepository repo, String email) {
        repo.findByEmail(email).ifPresent(m -> {
            throw new DuplicateEmailException(email);
        });
    }
}
