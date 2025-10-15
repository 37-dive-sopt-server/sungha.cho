package org.sopt.service;

import org.sopt.domain.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberService {

    Long join(String name, String email, LocalDate birth, String gender);

    Optional<Member> findOne(Long memberId);

    List<Member> findAllMembers();

    boolean deleteMember(Long memberId);
}
