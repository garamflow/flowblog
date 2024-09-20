package com.github.garamflow.flowblog.dto;

import com.github.garamflow.flowblog.domain.Article;

import java.time.LocalDateTime;
import java.util.List;

public record UpdateArticleRequest(
        String title,
        String content,
        List<String> tags,
        LocalDateTime updatedAt,
        String categoryName
) {
    public void updateArticle(Article article) {
        article.update(title, content, tags, updatedAt, categoryName);
    }
}