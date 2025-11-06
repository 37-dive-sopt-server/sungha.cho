package org.sopt.article.service;

import lombok.*;
import org.sopt.article.domain.Article;
import org.sopt.article.domain.Tag;
import org.sopt.article.dto.request.ArticleCreateDto;
import org.sopt.article.dto.response.ArticleInfoDto;
import org.sopt.article.repository.ArticleRepository;
import org.sopt.global.exception.BusinessException;
import org.sopt.member.domain.Member;
import org.sopt.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.sopt.global.exception.constant.ErrorCode.MEMBER_NOT_FOUND;
import static org.sopt.global.exception.constant.ErrorCode.ARTICLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Override
    public ArticleInfoDto create(ArticleCreateDto req) {
        // 작성자 조회
        Member author = memberRepository.findById(req.memberId())
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));

        // 태그 파싱 (CS/DB/SPRING/ETC)
        Tag tag = Tag.fromString(req.tag());

        // 엔티티 생성 (createdAt 기본값 now)
        Article saved = articleRepository.save(
                Article.builder()
                        .member(author)
                        .title(req.title())
                        .content(req.content())
                        .tag(tag)
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        return ArticleInfoDto.from(saved);
    }

    @Override
    public ArticleInfoDto findOne(Long articleId) {
        Article a = articleRepository.findById(articleId)
                .orElseThrow(() -> new BusinessException(ARTICLE_NOT_FOUND));
        return ArticleInfoDto.from(a);
    }
}
