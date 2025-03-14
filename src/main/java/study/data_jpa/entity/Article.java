package study.data_jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime publishedAt;

    public Article(String title, String content, LocalDateTime publishedAt) {
        this.title = title;
        this.content = content;
        this.publishedAt = publishedAt;
    }

    // Getter, Setter (필요 시)
}
