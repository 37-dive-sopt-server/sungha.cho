package org.sopt.article.service;

import lombok.RequiredArgsConstructor;
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
import java.util.List;

import static org.sopt.global.exception.constant.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Override
    public ArticleInfoDto create(ArticleCreateDto req) {
        Member author = memberRepository.findById(req.memberId())
                .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));

        Tag tag = Tag.fromString(req.tag());

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

    @Override
    public List<ArticleInfoDto> findAll() {
        List<Article> list = articleRepository.findAllByOrderByCreatedAtDesc();
        if (list.isEmpty()) throw new BusinessException(EMPTY_ARTICLE_LIST);
        return list.stream().map(ArticleInfoDto::from).toList();
    }
}
