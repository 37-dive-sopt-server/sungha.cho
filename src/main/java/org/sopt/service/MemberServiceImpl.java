package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.dto.response.MemberInfoDto;
import org.sopt.dto.request.MemberCreateDto;
import org.sopt.exception.customexception.DuplicateEmailException;
import org.sopt.exception.customexception.EmptyMemberListException;
import org.sopt.exception.customexception.MemberNotFoundException;
import org.sopt.validator.MemberValidator;
import org.sopt.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberInfoDto join(MemberCreateDto req) {
        LocalDate birth = LocalDate.parse(req.birthDate()); // "YYYY-MM-DD"
        int age = Period.between(birth, LocalDate.now()).getYears();
        MemberValidator.validate(req.name(), age);

        memberRepository.findByEmail(req.email())
                .ifPresent(m -> { throw new DuplicateEmailException(req.email()); });

        Gender gender = Gender.fromString(req.gender());

        Member saved = memberRepository.save(
                new Member(null, req.name(), req.email(), birth, gender)
        );

        return MemberInfoDto.from(saved);
    }

    @Override
    public MemberInfoDto findOne(Long memberId) {
        Member m = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        return MemberInfoDto.from(m);
    }

    @Override
    public List<MemberInfoDto> findAllMembers() {
        List<Member> list = memberRepository.findAll();
        if (list.isEmpty()) throw new EmptyMemberListException();
        return list.stream().map(MemberInfoDto::from).toList();
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        memberRepository.deleteById(memberId);
    }
}
