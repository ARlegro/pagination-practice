package study.data_jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.*;
@EqualsAndHashCode(callSuper = true)

@Entity
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@ToString(of = {"name"})
public class Team extends RealBaseEntity{

    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
