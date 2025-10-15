package org.sopt.validator;

import org.sopt.repository.MemberRepository;

public class MemberValidator {

    public void checkDuplicateEmail(MemberRepository repo, String email) {
        repo.findByEmail(email).ifPresent(m -> {
            throw new IllegalStateException("이미 등록된 이메일입니다: " + email);
        });
    }

}
