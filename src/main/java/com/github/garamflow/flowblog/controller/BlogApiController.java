package com.github.garamflow.flowblog.controller;

import com.github.garamflow.flowblog.domain.Article;
import com.github.garamflow.flowblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        Article savedArticle = blogService.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }
}
