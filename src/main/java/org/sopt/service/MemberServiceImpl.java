package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.validator.MemberValidator;
import org.sopt.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberValidator validator;
    private static long sequence = 1L;

    public MemberServiceImpl(MemberRepository memberRepository, MemberValidator validator) {
        this.memberRepository = memberRepository;
        this.validator = validator;
    }

    @Override
    public Long join(String name, String email, LocalDate birth, Gender gender) {
        validator.validate(memberRepository, email, birth);

        Member member = new Member(sequence++, name, email, birth, gender);
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public boolean deleteMember(Long memberId) {
        return memberRepository.deleteById(memberId);
    }
}
