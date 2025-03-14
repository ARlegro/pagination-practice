package study.data_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity @AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends RealBaseEntity{
    private String title;

    private LocalDate publicationDate;
}
