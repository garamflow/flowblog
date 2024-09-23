package com.github.garamflow.flowblog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.garamflow.flowblog.domain.Article;
import com.github.garamflow.flowblog.dto.AddArticleRequest;
import com.github.garamflow.flowblog.dto.UpdateArticleRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
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
        final List<String> tags = new ArrayList<>();
        final String categoryName = "카테고리";

        final AddArticleRequest userRequest = new AddArticleRequest(title, content, author, tags, categoryName);

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
        assertThat(articles.get(0).getCreatedAt()).isNotNull();
        assertThat(articles.get(0).getUpdatedAt()).isNotNull();
        assertThat(articles.get(0).getTags().size()).isEqualTo(tags.size());
        assertThat(articles.get(0).getCategoryName()).isEqualTo(categoryName);
    }

    @DisplayName("getAllArticles: 블로그 글 목록 조회에 성공한다.")
    @Test
    void getAllArticles() throws Exception {
        final String url = "/api/articles";
        final String title = "타이틀";
        final String content = "내용";
        final String author = "저자";
        final List<String> tags = List.of("Java", "Spring");
        final String categoryName = "카테고리";

        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .tags(tags)
                .categoryName(categoryName)
                .build());

        //when
        final ResultActions resultActions = mockMvc.perform(get(url).accept(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title))
                .andExpect(jsonPath("$[0].author").value(author))
                .andExpect(jsonPath("$[0].categoryName").value(categoryName))
                .andExpect(jsonPath("$[0].tags[0]").value("Java"))
                .andExpect(jsonPath("$[0].tags[1]").value("Spring"))
                .andExpect(jsonPath("$[0].createdAt").isNotEmpty())
                .andExpect(jsonPath("$[0].updatedAt").isNotEmpty());
    }

    @DisplayName("findArticle: 블로그 글 하나 조회에 성공한다.")
    @Test
    public void findArticle() throws Exception {
        //given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";
        final String author = "저자";
        final LocalDateTime createdAt = LocalDateTime.now();
        final LocalDateTime updatedAt = LocalDateTime.now();
        final List<String> tags = List.of("Java", "Spring");
        final String categoryName = "카테고리";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .tags(tags)
                .categoryName(categoryName)
                .build());

        //when
        final ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.author").value(author))
                .andExpect(jsonPath("$.categoryName").value(categoryName))
                .andExpect(jsonPath("$.tags[0]").value("Java"))
                .andExpect(jsonPath("$.tags[1]").value("Spring"))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andExpect(jsonPath("$.updatedAt").isNotEmpty());
    }

    @DisplayName("deleteArticle: 블로그 글 하나 삭제에 성공한다.")
    @Test
    public void deleteArticle() throws Exception {
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";
        final String author = "저자";
        final LocalDateTime createdAt = LocalDateTime.now();
        final LocalDateTime updatedAt = LocalDateTime.now();
        final List<String> tags = List.of("Java", "Spring");
        final String categoryName = "카테고리";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .tags(tags)
                .categoryName(categoryName)
                .build());

        //when
        mockMvc.perform(delete(url, savedArticle.getId()));

        //then
        List<Article> articles = blogRepository.findAll();
        assertThat(articles).isEmpty();
    }

    @DisplayName("updatedArticle: 블로그 글 수정에 성공한다.")
    @Test
    @Transactional
    public void updateArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";
        final String author = "저자";
        final List<String> tags = List.of("Java", "Spring");
        final String categoryName = "카테고리";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .author(author)
                .tags(tags)
                .categoryName(categoryName)
                .build());

        final String newTitle = "newTitle";
        final String newContent = "newContent";
        final List<String> newTags = Arrays.asList("newTags", "newTags2");
        final String newCategoryName = "newCategoryName";

        UpdateArticleRequest request = new UpdateArticleRequest(newTitle, newContent, newTags, newCategoryName);

        //when
        ResultActions result = mockMvc.perform(put(url, savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(request)));

        //then
        result.andExpect(status().isOk());
        Article article = blogRepository.findById(savedArticle.getId()).get();

        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);
        assertThat(article.getTags()).isEqualTo(newTags);
        assertThat(article.getCategoryName()).isEqualTo(newCategoryName);
        assertThat(article.getUpdatedAt()).isAfterOrEqualTo(article.getCreatedAt());
    }
}