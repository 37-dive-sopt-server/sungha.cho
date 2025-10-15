package org.sopt.service; // 서비스 구현체가 위치한 패키지

import org.sopt.domain.Member;                   // 도메인 객체(Member) 사용
import org.sopt.repository.MemoryMemberRepository; // 메모리 저장소 사용

import java.util.List;     // 전체 조회 반환 타입
import java.util.Optional; // 단일 조회 반환 타입

public class MemberServiceImpl implements MemberService { // 인터페이스를 실제로 구현

    // 현재 구조: 구현체 내부에서 저장소 구현체를 직접 생성(new) → 결합도 높음
    private MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    // 회원 ID 자동 증가를 위한 간단한 카운터(애플리케이션 실행 중에만 유효, 재시작하면 초기화됨)
    private static long sequence = 1L;

    // 회원 가입(등록) 로직: 이름을 받아서 새로운 Member를 만들고 저장한 뒤, 생성된 ID를 반환
    public Long join(String name) {
        Member member = new Member(sequence++, name); // 현재 sequence 값을 ID로 쓰고, 다음을 위해 1 증가
        memberRepository.save(member);                // 저장소에 새 회원 저장
        return member.getId();                        // 생성된 회원의 고유 ID를 반환
    }

    // 단일 회원 조회: ID로 저장소에서 찾아 Optional<Member>로 반환
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId); // 저장소 위임
    }

    // 전체 회원 조회: 저장소에서 모든 회원을 List<Member>로 받아 그대로 반환
    public List<Member> findAllMembers() {
        return memberRepository.findAll(); // 저장소 위임
    }
}
