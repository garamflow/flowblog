package com.github.garamflow.flowblog.service;

import com.github.garamflow.flowblog.domain.Article;
import com.github.garamflow.flowblog.dto.UpdateArticleRequest;
import com.github.garamflow.flowblog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(Article article) {
        return blogRepository.save(article);
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new NoSuchElementException("not found" + id));
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id).orElseThrow(() -> new NoSuchElementException("not found" + id));
        article.update(request.title(), request.content(), request.tags(), request.categoryName());

        return article;
    }
}
