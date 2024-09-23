-- article 테이블에 데이터 삽입
INSERT INTO article (title, content, author, category_name, created_at, updated_at)
VALUES ('Spring Boot Guide', 'This is a guide to Spring Boot', 'Author1', 'Programming', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- article_tags 테이블에 태그 삽입 (article 삽입 후 실행)
INSERT INTO article_tags (article_id, tags) VALUES (1, 'Spring');
INSERT INTO article_tags (article_id, tags) VALUES (1, 'Java');

-- 두 번째 글에 대해 같은 방식으로 반복

INSERT INTO article (title, content, author, category_name, created_at, updated_at)
VALUES ('Thymeleaf with Spring', 'Learn how to use Thymeleaf with Spring', 'Author2', 'Web Development', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO article_tags (article_id, tags) VALUES (2, 'Thymeleaf');
INSERT INTO article_tags (article_id, tags) VALUES (2, 'Template');