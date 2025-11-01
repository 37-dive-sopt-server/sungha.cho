package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.dto.response.MemberInfoDto;
import org.sopt.dto.request.MemberCreateDto;
import static org.sopt.global.exception.constant.ErrorCode.*;
import org.sopt.global.exception.MemberException;
import org.sopt.global.validator.AgeValidator;
import org.sopt.global.validator.NameValidator;
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
        LocalDate birth = LocalDate.parse(req.birthDate());
        int age = Period.between(birth, LocalDate.now()).getYears();
        NameValidator.validateName(req.name());
        AgeValidator.checkAdult(age);

        if (memberRepository.findByEmail(req.email()).isPresent()) {
            throw new MemberException(DUPLICATE_EMAIL);
        }

        Gender gender = Gender.fromString(req.gender());

        Member saved = memberRepository.save(
                new Member(null, req.name(), req.email(), birth, gender)
        );

        return MemberInfoDto.from(saved);
    }

    @Override
    public MemberInfoDto findOne(Long memberId) {
        Member m = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
        return MemberInfoDto.from(m);
    }

    @Override
    public List<MemberInfoDto> findAllMembers() {
        List<Member> list = memberRepository.findAll();
        if (list.isEmpty()) throw new MemberException(EMPTY_MEMBER_LIST);
        return list.stream().map(MemberInfoDto::from).toList();
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
        memberRepository.deleteById(memberId);
    }
}
