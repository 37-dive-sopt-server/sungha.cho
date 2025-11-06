package org.sopt.member.controller;

import lombok.*;
import org.sopt.member.dto.request.MemberCreateDto;
import org.sopt.member.dto.response.MemberInfoDto;
import org.sopt.member.service.MemberService;
import org.sopt.global.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ApiResponse<MemberInfoDto> create(@RequestBody MemberCreateDto req) {
        MemberInfoDto member = memberService.join(req);
        return ApiResponse.ok(member);
    }

    @GetMapping("/{memberId}")
    public ApiResponse<MemberInfoDto> getMember(@PathVariable Long memberId) {
        MemberInfoDto member = memberService.findOne(memberId);
        return ApiResponse.ok(member);
    }

    @GetMapping
    public ApiResponse<List<MemberInfoDto>> getAllMembers() {
        List<MemberInfoDto> members = memberService.findAllMembers();
        return ApiResponse.ok(members);
    }

    @DeleteMapping("/{memberId}")
    public ApiResponse<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ApiResponse.ok(null);
    }
}
