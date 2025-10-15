package org.sopt.config;

import org.sopt.controller.MemberController;
import org.sopt.validator.MemberValidator;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.MemoryMemberRepository;
import org.sopt.service.MemberService;
import org.sopt.service.MemberServiceImpl;

public class AppConfig {

    public MemberRepository memberRepository() { // 메모리에 회원을 저장/조회할 저장소 인스턴스 생성
        return new MemoryMemberRepository();
    }

    public MemberValidator memberValidator() {        // 유효성 검증을 위한 validator 인스턴스 생성
        return new MemberValidator();
    }

    public MemberService memberService() { // 비즈니스 로직을 담당할 서비스 인스턴스 생성
        return new MemberServiceImpl(memberRepository(), memberValidator()); // memberValidator까지 포함
    }

    public MemberController memberController() { // 요청을 받아 서비스로 전달할 컨트롤러 인스턴스 생성
        return new MemberController(memberService());
    }
}
