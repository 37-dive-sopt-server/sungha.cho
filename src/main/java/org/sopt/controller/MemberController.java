package org.sopt.controller;

import org.sopt.domain.Member;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Long createMember(String name, String email, LocalDate birth, String genderInput) {
        return memberService.join(name, email, birth, genderInput);
    }

    @GetMapping("/{id}")
    public Member findMemberById(Long id) {
        return memberService.findOne(id);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    @DeleteMapping("/{id}")
    public void deleteMember(Long id) {
        memberService.deleteMember(id);
    }
}
