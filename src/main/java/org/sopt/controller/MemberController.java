package org.sopt.controller;

import org.sopt.domain.Member;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/users")
    public Long createMember(String name, String email, LocalDate birth, String genderInput) {
        return memberService.join(name, email, birth, genderInput);
    }

    @GetMapping("/users")
    public Member findMemberById(Long id) {
        return memberService.findOne(id);
    }

    @GetMapping("/users/all")
    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    public void deleteMember(Long id) {
        memberService.deleteMember(id);
    }
}
