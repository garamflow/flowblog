package com.github.garamflow.flowblog.dto;

import com.github.garamflow.flowblog.domain.Article;
import java.time.LocalDateTime;
import java.util.List;

public record AddArticleRequest(
        String title,
        String content,
        String author,
        LocalDateTime createdAt,
        LocalDateTime updateAt,
        List<String> tags,
        String categoryName
) {
    public Article toArticle() {
        return Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .createdAt(createdAt)
                .updatedAt(updateAt)
                .tags(tags)
                .categoryName(categoryName)
                .build();
    }
}
