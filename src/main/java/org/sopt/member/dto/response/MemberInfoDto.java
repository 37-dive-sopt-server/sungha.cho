package org.sopt.member.dto.response;

import org.sopt.member.domain.Member;
import org.sopt.member.domain.Gender;

import java.time.LocalDate;

public record MemberInfoDto(
        Long memberId,
        String name,
        LocalDate birthDate,
        String email,
        Gender gender
) {
    public static MemberInfoDto from(Member member) {
        return new MemberInfoDto(
                member.getId(),
                member.getName(),
                member.getBirth(),
                member.getEmail(),
                member.getGender()
        );
    }
}
