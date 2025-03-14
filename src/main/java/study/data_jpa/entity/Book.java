package study.data_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id @GeneratedValue
    @Column(name = "author_id")
    private Long id;

    private String title;

    private LocalDate publicationDate;

    public Book(String title, LocalDate publicationDate) {
        this.title = title;
        this.publicationDate = publicationDate;
    }


}
