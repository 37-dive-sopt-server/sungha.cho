package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.dto.response.MemberInfoDto;
import org.sopt.dto.request.MemberCreateDto;
import static org.sopt.global.exception.constant.ErrorCode.*;
import org.sopt.global.exception.BusinessException;
import org.sopt.global.validator.MemberValidator;
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

        if (memberRepository.findByEmail(req.email()).isPresent()) {
            throw new BusinessException(DUPLICATE_EMAIL, req.email());
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
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND, memberId));
        return MemberInfoDto.from(m);
    }

    @Override
    public List<MemberInfoDto> findAllMembers() {
        List<Member> list = memberRepository.findAll();
        if (list.isEmpty()) throw new BusinessException(EMPTY_MEMBER_LIST);
        return list.stream().map(MemberInfoDto::from).toList();
    }

    @Override
    public void deleteMember(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND, memberId));
        memberRepository.deleteById(memberId);
    }
}
