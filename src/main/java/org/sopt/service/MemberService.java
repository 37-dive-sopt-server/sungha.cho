package org.sopt.service; // 서비스 계층 패키지

import org.sopt.domain.Member; // 반환 타입/파라미터로 Member를 사용하므로 import

import java.util.List;     // 전체 조회 반환 타입
import java.util.Optional; // 단일 조회 반환 타입

public interface MemberService { // 서비스가 제공해야 할 기능(메서드 시그니처)만 정의

    // 회원 가입(등록): 이름만 받아 내부에서 ID를 만들어 저장, 생성된 회원의 ID를 반환
    Long join(String name);

    // 단일 회원 조회: 회원 ID로 조회(없을 수 있으므로 Optional로 감싸서 반환)
    Optional<Member> findOne(Long memberId);

    // 전체 회원 조회: 저장된 모든 회원을 리스트로 반환
    List<Member> findAllMembers();
}
