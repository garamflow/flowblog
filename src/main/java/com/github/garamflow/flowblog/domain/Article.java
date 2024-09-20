package com.github.garamflow.flowblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    @ElementCollection
    @Column(name = "tags", nullable = true)
    private List<String> tags;

    @Column(name="categoryName", nullable = false)
    private String categoryName;

    @Builder(toBuilder = true)
    public Article(String title, String content, String author, LocalDateTime createdAt, LocalDateTime updatedAt, List<String> tags, String categoryName) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
        this.tags = tags;
        this.categoryName = categoryName;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String title, String content, List<String> tags, LocalDateTime updatedAt, String categoryName) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
        this.categoryName = categoryName;
    }
}
