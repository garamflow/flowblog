package com.github.garamflow.flowblog.dto;

import com.github.garamflow.flowblog.domain.Article;

import java.time.LocalDateTime;
import java.util.List;

public record ArticleViewResponse(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        List<String> tags,
        String categoryName
) {
    public ArticleViewResponse(Article article) {
        this(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getAuthor(),
                article.getCreatedAt(),
                article.getTags(),
                article.getCategoryName()
        );
    }
}
