package com.github.garamflow.flowblog.dto;

import com.github.garamflow.flowblog.domain.Article;

import java.util.List;

public record UpdateArticleRequest(
        String title,
        String content,
        List<String> tags,
        String categoryName
) {
    public void updateArticle(Article article) {
        article.update(title, content, tags, categoryName);
    }
}