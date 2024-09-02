package com.github.garamflow.flowblog.dto;

import com.github.garamflow.flowblog.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private List<String> tags;
    private String categoryName;

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
