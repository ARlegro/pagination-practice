package study.data_jpa.basic;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import study.data_jpa.entity.Book;
import study.data_jpa.repository.BookRepository;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class BookPaging {

    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll(); // 매 테스트 시작 전에 테이블 초기화

        // 테스트용 Book 데이터 저장
        bookRepository.saveAll(List.of(
                new Book("The Great Gatsby", LocalDate.of(1925, 4, 10)),
                new Book("The Adventures of Tom Sawyer", LocalDate.of(1876, 6, 1)),
                new Book("The Lord of the Rings", LocalDate.of(1954, 7, 29)),
                new Book("Thinking in Java", LocalDate.of(1998, 1, 1)),
                new Book("Spring in Action", LocalDate.of(2020, 3, 10)),
                new Book("Effective Java", LocalDate.of(2018, 5, 8))
        ));
    }

    @Test
    void testFindByTitleContaining() {
        // title에 "The"가 포함된 Book 조회
        Pageable pageRequest = PageRequest.of(0, 2); // 페이지 번호 0, 페이지 크기 2
        Page<Book> pagingBooks;

        do {
            System.out.println("=========================");
            pagingBooks = bookRepository.findJPQLContainingTitle("The", pageRequest);
            System.out.println("Total Pages: " + pagingBooks.getTotalPages());
            System.out.println("Total Elements: " + pagingBooks.getTotalElements());
            System.out.println("Page Number: " + pagingBooks.getNumber());
            System.out.println("Page Size: " + pagingBooks.getSize());
            List<Book> content = pagingBooks.getContent();
            for (Book book : content) {
                System.out.println("Book Title: " + book.getTitle());
                System.out.println("Publication Date: " + book.getPublicationDate());
            }
            pageRequest = pagingBooks.nextPageable();  // 다음 페이지 요청
            System.out.println("=========================");
        } while (pagingBooks.hasNext()); // 다음 페이지가 있는 경우 반복
    }


    @Test
    void testFindByTitleContainingAfter() {
        // title에 "The"가 포함된 Book 조회
        Pageable pageRequest = PageRequest.of(0, 2); // 페이지 번호 0, 페이지 크기 2
        Page<Book> pagingBooks;

        do {
            System.out.println("=========================");
            pagingBooks = bookRepository.findJPQLContainingTitleAfter("The", LocalDate.of(1950, 1,1) ,pageRequest);
            System.out.println("Total Pages: " + pagingBooks.getTotalPages());
            System.out.println("Total Elements: " + pagingBooks.getTotalElements());
            System.out.println("Page Number: " + pagingBooks.getNumber());
            System.out.println("Page Size: " + pagingBooks.getSize());
            List<Book> content = pagingBooks.getContent();
            for (Book book : content) {
                System.out.println("Book Title: " + book.getTitle());
                System.out.println("Publication Date: " + book.getPublicationDate());
            }
            pageRequest = pagingBooks.nextPageable();  // 다음 페이지 요청
            System.out.println("=========================");
        } while (pagingBooks.hasNext()); // 다음 페이지가 있는 경우 반복
    }



}
