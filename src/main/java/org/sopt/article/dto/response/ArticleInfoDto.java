package org.sopt.article.dto.response;

import org.sopt.article.domain.Article;
import org.sopt.article.domain.Tag;

import java.time.LocalDateTime;

public record ArticleInfoDto(
        Long articleId,
        Long memberId,
        String title,
        String content,
        Tag tag,
        LocalDateTime createdAt
) {
    public static ArticleInfoDto from(Article a) {
        return new ArticleInfoDto(
                a.getId(),
                a.getMember().getId(),
                a.getTitle(),
                a.getContent(),
                a.getTag(),
                a.getCreatedAt()
        );
    }
}
