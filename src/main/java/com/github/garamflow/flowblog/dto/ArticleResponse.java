package com.github.garamflow.flowblog.dto;

import com.github.garamflow.flowblog.domain.Article;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ArticleResponse {

    private final Long id;
    private final String title;
    private final String author;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final List<String> tags;
    private final String categoryName;

    public ArticleResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.author = article.getAuthor();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();
        this.tags = article.getTags();
        this.categoryName = article.getCategoryName();
    }
}
