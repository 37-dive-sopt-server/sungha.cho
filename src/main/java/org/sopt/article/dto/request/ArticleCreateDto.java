package org.sopt.article.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ArticleCreateDto(
        @NotNull(message = "작성자(memberId)를 입력해주세요.")
        Long memberId,

        @NotBlank(message = "제목을 입력해주세요.")
        String title,

        @NotBlank(message = "내용을 입력해주세요.")
        String content,

        @NotBlank(message = "태그를 입력해주세요. (CS/DB/SPRING/ETC)")
        String tag
) {}
