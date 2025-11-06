package org.sopt.article.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.article.dto.request.ArticleCreateDto;
import org.sopt.article.dto.response.ArticleInfoDto;
import org.sopt.article.service.ArticleService;
import org.sopt.global.response.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    // 생성
    @PostMapping
    public ApiResponse<ArticleInfoDto> create(@RequestBody @Valid ArticleCreateDto req) {
        return ApiResponse.ok(articleService.create(req));
    }

    // 단일 조회
    @GetMapping("/{articleId}")
    public ApiResponse<ArticleInfoDto> getOne(@PathVariable Long articleId) {
        return ApiResponse.ok(articleService.findOne(articleId));
    }

    // 전체 조회
    @GetMapping
    public ApiResponse<List<ArticleInfoDto>> getAll() {
        return ApiResponse.ok(articleService.findAll());
    }
}
