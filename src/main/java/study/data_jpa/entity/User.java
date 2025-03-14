package study.data_jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users") // 테이블 이름을 users로 설정
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String name;
    private int point;

    public User(String name, int point) {
        this.name = name;
        this.point = point;
    }

    public void addPoint(int point) {
        this.point += point;
    }
}
