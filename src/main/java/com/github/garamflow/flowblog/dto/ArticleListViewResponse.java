package com.github.garamflow.flowblog.dto;

import com.github.garamflow.flowblog.domain.Article;
import java.time.LocalDateTime;
import java.util.List;

public record ArticleListViewResponse(
        Long id,
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<String> tags,
        String categoryName
) {
    public ArticleListViewResponse(Article article) {
        this(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getAuthor(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                article.getTags(),
                article.getCategoryName()
        );
    }
}
