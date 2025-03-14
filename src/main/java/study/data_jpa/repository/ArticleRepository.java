package study.data_jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import study.data_jpa.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByTitleContaining(String title, Pageable pageable);

    // 제목에 키워드가 포함된 기사 페이징 조회

    // (추가 도전) 대소문자 구분 없이 검색

}
