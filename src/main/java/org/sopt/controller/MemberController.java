package org.sopt.controller; // 컨트롤러가 위치한 패키지

import org.sopt.domain.Member;         // 컨트롤러에서 Member 타입을 반환/사용하므로 import
import org.sopt.service.MemberService; // 서비스 기능(인터페이스)을 사용
import org.sopt.service.MemberServiceImpl; // 현재는 구현체를 직접 new로 생성하고 있음(결합도 상승)

import java.util.List;     // 전체 목록 반환 타입
import java.util.Optional; // 단일 조회 반환 타입(값이 없을 수도 있음)

public class MemberController { // 컨트롤러 클래스: Main(또는 외부)에서 호출되는 진입점

    // 외부(Main)에서 주입받을 서비스 - final로 선언
    private final MemberService memberService;

    // 생성자에서 의존성 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 이름만 받아 회원 등록을 요청하는 컨트롤러 메서드
    public Long createMember(String name) {
        // 서비스 계층에 '회원 가입'을 위임하고, 생성된 회원의 ID를 반환받아 그대로 리턴
        return memberService.join(name);
    }

    // ID로 회원 한 명을 조회하는 컨트롤러 메서드
    public Optional<Member> findMemberById(Long id) {
        // 서비스 계층에 '단일 회원 조회'를 위임
        return memberService.findOne(id);
    }

    // 전체 회원 조회하는 컨트롤러 메서드
    public List<Member> getAllMembers() {
        // 서비스 계층에 '전체 회원 조회'를 위임
        return memberService.findAllMembers();
    }
}
