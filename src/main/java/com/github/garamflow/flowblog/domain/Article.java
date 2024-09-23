package com.github.garamflow.flowblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    @Column(name = "author", nullable = true)
    private String author;

    @CreatedDate
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    @ElementCollection
    @Column(name = "tags", nullable = true)
    private List<String> tags;

    @Column(name="categoryName", nullable = true)
    private String categoryName;

    @Builder(toBuilder = true)
    public Article(String title, String content, String author, List<String> tags, String categoryName) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.tags = tags;
        this.categoryName = categoryName;
    }

    public void update(String title, String content, List<String> tags, String categoryName) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.categoryName = categoryName;
    }
}
