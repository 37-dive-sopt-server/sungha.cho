package org.sopt.validator;

import org.sopt.repository.MemberRepository;
import org.sopt.exception.DuplicateEmailException;

public class MemberValidator {

    public void checkDuplicateEmail(MemberRepository repo, String email) {
        repo.findByEmail(email).ifPresent(m -> {
            throw new DuplicateEmailException(email);
        });
    }

}
