package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.exception.DuplicateEmailException;
import org.sopt.exception.EmptyMemberListException;
import org.sopt.exception.MemberNotFoundException;
import org.sopt.validator.MemberValidator;
import org.sopt.repository.MemberRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private static long sequence = 1L;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Long join(String name, String email, LocalDate birth, String genderInput) {
        int age = Period.between(birth, LocalDate.now()).getYears();
        MemberValidator.validate(name, age, genderInput);

        memberRepository.findByEmail(email).ifPresent(m -> {
            throw new DuplicateEmailException(email);
        });

        Gender gender = Gender.valueOf(genderInput.toUpperCase());
        Member member = new Member(sequence++, name, email, birth, gender);
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    @Override
    public List<Member> findAllMembers() {
        List<Member> members = memberRepository.findAll();
        if (members.isEmpty()) throw new EmptyMemberListException();
        return members;
    }

    @Override
    public void deleteMember(Long memberId) {
        boolean deleted = memberRepository.deleteById(memberId);
        if (!deleted) throw new MemberNotFoundException(memberId);
    }
}
