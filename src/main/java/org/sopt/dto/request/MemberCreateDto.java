package org.sopt.dto.request;

import java.time.LocalDate;

public record MemberCreateDto(
        // 검증 추가
        String name,
        String birthDate,
        String email,
        String gender
) {}