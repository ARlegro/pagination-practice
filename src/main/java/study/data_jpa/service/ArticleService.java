package study.data_jpa.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.KeysetScrollPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.ScrollPosition;
import org.springframework.data.support.WindowIterator;
import org.springframework.stereotype.Service;
import study.data_jpa.entity.Article;
import study.data_jpa.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

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
//        article.forEachRemaining((a) -> {
//            articleList.add(a);
//            System.out.println("forEachRemaining 중인 = " + a);
//            // 이게 다음거까지 가져오나
//        });
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
}
