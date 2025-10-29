package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.exception.customexception.DuplicateEmailException;
import org.sopt.exception.customexception.EmptyMemberListException;
import org.sopt.exception.customexception.MemberNotFoundException;
import org.sopt.validator.MemberValidator;
import org.sopt.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Long join(String name, String email, LocalDate birth, String genderInput) {
        int age = Period.between(birth, LocalDate.now()).getYears();
        MemberValidator.validate(name, age);
        memberRepository.findByEmail(email).ifPresent(m -> {
            throw new DuplicateEmailException(email);
        });
        Gender gender = Gender.fromString(genderInput);

        Member member = new Member(null, name, email, birth, gender);
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
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
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));

        memberRepository.deleteById(memberId);
    }
}
