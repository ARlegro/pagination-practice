package study.data_jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.data_jpa.entity.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContaining(String title);

    // 예시: title에 특정 문자열이 포함된 결과를 publicationDate 순서대로 페이지 조회
    Page<Book> findByTitleContainingOrderByPublicationDateAsc(String title, Pageable pageable);

    @Query("SELECT b FROM Book b Where b.title like %:title%")
    Page<Book> findJPQLContainingTitle(@Param("title") String title, Pageable pageable);

    @Query("SELECT b FROM Book b Where b.title like %:title% AND b.publicationDate > :publicationDate")
    Page<Book> findJPQLContainingTitleAfter(@Param("title") String title, @Param("publicationDate")LocalDate publicationDate, Pageable pageable);
}
