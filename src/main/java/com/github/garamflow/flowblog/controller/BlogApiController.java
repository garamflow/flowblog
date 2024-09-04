package com.github.garamflow.flowblog.controller;

import com.github.garamflow.flowblog.domain.Article;
import com.github.garamflow.flowblog.dto.ArticleResponse;
import com.github.garamflow.flowblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        Article savedArticle = blogService.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> getAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream().map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok(articles);
    }
}
