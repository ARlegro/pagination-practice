package study.data_jpa.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.support.WindowIterator;
import org.springframework.stereotype.Service;
import study.data_jpa.entity.Article;
import study.data_jpa.repository.ArticleRepository;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getArticlesByIterator() {
        System.out.println("쿼리 나가기 전: ============================");
        WindowIterator<Article> article = WindowIterator.of((position ->
                articleRepository.findFirst10ByOrderByIdDesc((KeysetScrollPosition) position))).startingAt(ScrollPosition.keyset());
        System.out.println("쿼리 나가는거 테스트1: ============================");

        List<Article> articleList = new ArrayList<>();
        article.forEachRemaining(articleList::add);
        System.out.println("쿼리 나가는거 테스트2: ============================");
        return articleList;
    }

    public List<Article> getArticlesByWhile() {
        System.out.println("쿼리 나가기 전: ============================");
        WindowIterator<Article> article = WindowIterator.of((position ->
                articleRepository.findFirst10ByOrderByIdDesc((KeysetScrollPosition) position))).startingAt(ScrollPosition.keyset());
        System.out.println("쿼리 나가는거 테스트1: ============================");


        List<Article> articleList = new ArrayList<>();
        while (article.hasNext()) {
            var entity = article.next();
            //Article entity = article.next();
            System.out.println("entity = " + entity);
            articleList.add(entity);
        }
        System.out.println("articleList = " + articleList);
        System.out.println("쿼리 나가는거 테스트2: ============================");
        return articleList;
    }

    public List<Article> getArticlesByPage() {
        System.out.println("Page<T> 사용 시작: ============================");
        Pageable pageable = PageRequest.of(0, 10);
        List<Article> articleList = new ArrayList<>();
        Page<Article> articlePage;
        do {
            //articlePage = articleRepository.findAllBy(pageable);
            //articlePage = articleRepository.findPageAllByOrderByIdDesc(pageable);
            articlePage = articleRepository.findPageAllByOrderByCreatedAtDesc(pageable);
            articlePage.map(articleList::add);
            pageable = articlePage.nextPageable();
            log.info("현재 페이지 : {} 끝", articlePage.getNumber());
        } while (articlePage.hasNext());
        System.out.println("Page<T> 사용 끝: ============================");
        return articleList;
    }


    public List<ScrollPageResponse<Article>> getArticlesOrderByCreated() {
        System.out.println("생성 시작 시간 기준: ============================");
        long count = articleRepository.count();
        log.info("count = {}", count);
        KeysetScrollPosition position = ScrollPosition.keyset();

        List<ScrollPageResponse<Article>> responses = new ArrayList<>();
        while (true){
            Window<Article> articleWindow = articleRepository.findFirst10ByOrderByCreatedAtDesc(position);
            List<Article> content = articleWindow.getContent();

            Map<String, Object> keyset = new HashMap<>();
            Article lastArticle = content.get(content.size() - 1);
            keyset.put("createdAt", lastArticle.getCreatedAt());
            keyset.put("id", lastArticle.getId());
            position = ScrollPosition.forward(keyset);
            int size = content.size();
            boolean hasNext = articleWindow.hasNext();

            ScrollPageResponse<Article> scrollPageResponse = new ScrollPageResponse<>(content, position, size, hasNext, count);
            log.info("더하기 전 scrollArticle: {}", scrollPageResponse);
            responses.add(scrollPageResponse);
            if (!hasNext) {
                break;
            }
        }


        //        List<T> content,
        //        Object nextCursor,
        //        int size,  // 현재 페이지에서 반환된 개수
        //        boolean hasNext,
        //        Long totalElements)

        return responses;
    }
}
