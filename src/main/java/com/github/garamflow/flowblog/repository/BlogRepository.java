package com.github.garamflow.flowblog.repository;

import com.github.garamflow.flowblog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
