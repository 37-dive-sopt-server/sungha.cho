package org.sopt.controller;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.service.MemberService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public Long createMember(String name, String email, LocalDate birth, Gender gender) {
        return memberService.join(name, email, birth, gender);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberService.findOne(id);
    }

    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    public boolean deleteMember(Long id) {
        return memberService.deleteMember(id);
    }
}
