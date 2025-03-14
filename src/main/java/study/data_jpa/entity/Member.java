package study.data_jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.*;
@EqualsAndHashCode(callSuper = true)

@Entity
@Getter @Setter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@ToString(of = {"username", "age"})
public class Member extends BaseEntity {

    private String username;
    private int age;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "team_id") // FK 명
    // 만약 FK명을 명시하지 않으면 team_id로 설정됨
    // 원리 : 필드명 + _id
    // 만약 필드명이 TAM이라면 TAM_id로 설정됨
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
