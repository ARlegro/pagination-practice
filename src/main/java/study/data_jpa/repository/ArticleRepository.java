package study.data_jpa.repository;

import org.springframework.data.domain.KeysetScrollPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import study.data_jpa.entity.Article;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findByTitleContaining(String title, Pageable pageable);

    // 제목에 키워드가 포함된 기사 페이징 조회

    // id 역순으로 정렬하여 10개씩 조회
    // 파라미터로 Pageable이 아닌 KeysetScrollPosition 사용할 것

//    Window<Article> findToprderByIdDesc(Pageable pageable);

    List<Article> findAllBy();
    List<Article> findFirst10ByOrderByContentDesc();
    List<Article> findFirstByOrderByContentAsc();

    Window<Article> findFirst10ByOrderByIdDesc(KeysetScrollPosition position);
}
