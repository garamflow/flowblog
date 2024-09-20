package com.github.garamflow.flowblog.controller;

import com.github.garamflow.flowblog.dto.ArticleListViewResponse;
import com.github.garamflow.flowblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();

        System.out.println(articles); // 로그 출력

        model.addAttribute("articles", articles);

        return "articles.html";
    }
}
