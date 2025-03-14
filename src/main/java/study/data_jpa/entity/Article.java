package study.data_jpa.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Article extends RealBaseEntity {

    private String title;
    private String content;
    private LocalDateTime publishedAt;

}
