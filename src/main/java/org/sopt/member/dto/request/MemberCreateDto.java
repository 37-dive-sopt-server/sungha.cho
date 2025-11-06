package org.sopt.member.dto.request;

public record MemberCreateDto(
        String name,
        String birthDate,
        String email,
        String gender
) {}