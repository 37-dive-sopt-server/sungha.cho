package org.sopt.dto.request;

public record MemberCreateDto(
        String name,
        String birthDate,
        String email,
        String gender
) {}