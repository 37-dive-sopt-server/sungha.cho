package org.sopt.article.service;

import org.sopt.article.dto.request.ArticleCreateDto;
import org.sopt.article.dto.response.ArticleInfoDto;

public interface ArticleService {
    ArticleInfoDto create(ArticleCreateDto req);
    ArticleInfoDto findOne(Long articleId);
}
