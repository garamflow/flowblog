package com.github.garamflow.flowblog.dto;

import com.github.garamflow.flowblog.domain.Article;
import java.time.LocalDateTime;
import java.util.List;

public record ArticleResponse(
        Long id,
        String title,
        String author,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<String> tags,
        String categoryName
) {
    public ArticleResponse(Article article) {
        this(
                article.getId(),
                article.getTitle(),
                article.getAuthor(),
                article.getContent(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                article.getTags(),
                article.getCategoryName()
        );
    }
}