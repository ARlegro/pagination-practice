package study.data_jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
@Table(name = "users") // 테이블 이름을 users로 설정
public class User extends RealBaseEntity{

    private String name;
    private int point;

    public void addPoint(int point) {
        this.point += point;
    }
}
