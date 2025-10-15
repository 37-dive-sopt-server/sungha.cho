package org.sopt.service; // 서비스 구현체가 위치한 패키지

import org.sopt.domain.Gender;
import org.sopt.domain.Member;                   // 도메인 객체(Member) 사용
import org.sopt.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;     // 전체 조회 반환 타입
import java.util.Optional; // 단일 조회 반환 타입

public class MemberServiceImpl implements MemberService { // 인터페이스를 실제로 구현

    // 외부(Main)에서 주입받을 repository - final로 선언
    private final MemberRepository memberRepository;
    // 생성자를 통해 외부(Main)에서 Repository를 주입받음
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    private static long sequence = 1L;    // 회원 ID 자동 증가를 위한 간단한 카운터

    // 회원 가입(등록) 로직: 사용자 정보를 받아서 새로운 Member를 만들고 저장한 뒤, 생성된 ID를 반환
    @Override
    public Long join(String name, String email, LocalDate birth, Gender gender) {
        // 새로운 멤버 생성 전 이메일 중복 검사
        validateDuplicateEmail(email);

        Member member = new Member(sequence++, name, email, birth, gender); // 현재 sequence 값을 ID로 쓰고, 다음을 위해 1 증가
        memberRepository.save(member);                // 저장소에 새 회원 저장
        return member.getId();                        // 생성된 회원의 고유 ID를 반환
    }

    // 이메일 중복 검사
    private void validateDuplicateEmail(String email) {
        Optional<Member> existing = memberRepository.findByEmail(email);
        if (existing.isPresent()) {
            throw new IllegalStateException("이미 등록된 이메일입니다: " + email);
        }
    }

    // 단일 회원 조회: ID로 저장소에서 찾아 Optional<Member>로 반환
    @Override
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId); // 저장소 위임
    }

    // 전체 회원 조회: 저장소에서 모든 회원을 List<Member>로 받아 그대로 반환
    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll(); // 저장소 위임
    }

    // 회원 삭제: ID로 저장소에서 찾아 성공/실패 여부 반환
    @Override
    public boolean deleteMember(Long memberId) {
        return memberRepository.deleteById(memberId);
    }
}
