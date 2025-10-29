package org.sopt.dto.response;

import org.sopt.domain.Member;
import org.sopt.domain.Gender;

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
