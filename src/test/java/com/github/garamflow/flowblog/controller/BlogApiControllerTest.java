package com.github.garamflow.flowblog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.garamflow.flowblog.domain.Article;
import com.github.garamflow.flowblog.dto.AddArticleRequest;
import com.github.garamflow.flowblog.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    private BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        blogRepository.deleteAll();
    }

    @DisplayName("addArticle: 블로그 글 추가에 성공")
    @Test
    @Transactional
    public void addArticle() throws Exception {
        //given
        final String url = "/api/articles";
        final String title = "타이틀";
        final String content = "내용";
        final String author = "저자";
        final LocalDateTime createdAt = LocalDateTime.now();
        final LocalDateTime updatedAt = LocalDateTime.now();
        final List<String> tags = new ArrayList<>();
        final String categoryName = "카테고리";

        final AddArticleRequest userRequest = new AddArticleRequest(title, content, author, createdAt, updatedAt, tags, categoryName);

        final String requestBody = objectMapper.writeValueAsString(userRequest);

        //when
        ResultActions result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestBody));

        //then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);
        assertThat(articles.get(0).getAuthor()).isEqualTo(author);
        assertThat(articles.get(0).getCreatedAt().truncatedTo(ChronoUnit.MINUTES))
                .isEqualTo(createdAt.truncatedTo(ChronoUnit.MINUTES));
        assertThat(articles.get(0).getUpdatedAt().truncatedTo(ChronoUnit.MINUTES))
                .isEqualTo(updatedAt.truncatedTo(ChronoUnit.MINUTES));
        assertThat(articles.get(0).getTags().size()).isEqualTo(tags.size());
        assertThat(articles.get(0).getCategoryName()).isEqualTo(categoryName);
    }
}