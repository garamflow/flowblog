package com.github.garamflow.flowblog.dto;

import com.github.garamflow.flowblog.domain.Article;

import java.time.LocalDateTime;

public record UpdateArticleRequest(
        String title,
        String content,
        String tags,
        LocalDateTime updatedAt,
        String categoryName
) {
    public void updateArticle(Article article) {
        article.update(title, content, tags, updatedAt, categoryName);
    }
}