package org.sopt.service;

import org.sopt.domain.Member;

import java.time.LocalDate;
import java.util.List;

public interface MemberService {

    Long join(String name, String email, LocalDate birth, String gender);

    Member findOne(Long memberId);

    List<Member> findAllMembers();

    void deleteMember(Long memberId);
}
