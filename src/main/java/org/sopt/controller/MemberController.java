package org.sopt.controller;

import org.sopt.domain.Member;
import org.sopt.dto.request.MemberCreateDto;
import org.sopt.dto.response.MemberInfoDto;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public MemberInfoDto create(@RequestBody MemberCreateDto req) {
        return memberService.join(req);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<MemberInfoDto> getMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.findOne(memberId));
    }

    @GetMapping
    public ResponseEntity<List<MemberInfoDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }
}
