package org.sopt.config;

import org.sopt.controller.MemberController;
import org.sopt.validator.MemberValidator;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.MemoryMemberRepository;
import org.sopt.service.MemberService;
import org.sopt.service.MemberServiceImpl;

public class AppConfig {

    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public MemberValidator memberValidator() {
        return new MemberValidator();
    }

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository(), memberValidator());
    }

    public MemberController memberController() {
        return new MemberController(memberService());
    }
}
