package org.sopt.controller;

import org.sopt.domain.Member;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("members")
public class MemberController {

    @Autowired
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Long createMember(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String birth,
                             @RequestParam String gender) {
        return memberService.join(name, email, LocalDate.parse(birth), gender);
    }

    @GetMapping("/{memberId}")
    public Member getMember(@PathVariable Long id) {
        return memberService.findOne(id);
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }

    @DeleteMapping("/{memberId}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}
