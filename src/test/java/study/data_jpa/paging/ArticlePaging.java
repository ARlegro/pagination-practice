package study.data_jpa.paging;

import jakarta.annotation.PostConstruct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import study.data_jpa.entity.Article;
import study.data_jpa.repository.ArticleRepository;

import java.time.LocalDateTime;

@SpringBootTest
public class ArticlePaging {

    @Autowired
    ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleRepository.deleteAll();
        // 테스트용 데이터 20건 등록, 일부 제목에는 "Breaking" 키워드 포함
        for (int i = 1; i <= 20; i++) {
            String title = (i % 3 == 0) ? "Breaking News " + i : "Regular News " + i;
            articleRepository.save(new Article(title, "Content " + i, LocalDateTime.now().minusDays(i)));
        }
    }

    // 제목에 키워드가 포함된 기사 페이징 조회
    @Test
    void keywordPaging(){
        Pageable pageRequest = PageRequest.of(0, 4);
        Page<Article> articlePage;

        do {
            articlePage = articleRepository.findByTitleContaining("Regular", pageRequest);
            System.out.println("전체 페이지 = " + articlePage.getTotalPages());
            int currentPage = articlePage.getNumber();
            System.out.println("현재 페이지 = " + ++currentPage);
            System.out.println("articlePage.getContent() = " + articlePage.getContent());
            System.out.println("articlePage.getTotalElements() = " + articlePage.getTotalElements());
            Assertions.assertThat(articlePage.getSize()).isLessThanOrEqualTo(4);
            pageRequest = articlePage.nextPageable();
        } while (articlePage.hasNext());

//        Assertions.assertThat(articlePage.getTotalElements()).isEqualTo(14);
//        Assertions.assertThat(articlePage.getTotalPages()).isEqualTo(4);
    }
    // (추가 도전) 대소문자 구분 없이 검색


    // Slice

}
