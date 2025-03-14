package study.data_jpa.bulk;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.data_jpa.entity.Member;
import study.data_jpa.repository.MemberRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberBulK {


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @PostConstruct
    void init() {
        memberRepository.save(new Member("MemberA", 10));
        memberRepository.save(new Member("MemberB", 15));
        memberRepository.save(new Member("MemberC", 20));
        memberRepository.save(new Member("MemberD", 21));
        memberRepository.save(new Member("MemberE", 26));
    }

    @Test
    @Transactional
    void ageTest1() {
        int resultCount = memberRepository.bulkAge(20);
        Member memberE = memberRepository.findByUsername("MemberE");
        System.out.println("memberE.getAge() = " + memberE.getAge());
        // bulk연산이 일어나지 않았다 Cuz 아직 영속성 컨텍스트에 남아있기 때문
        assertThat(memberE.getAge()).isEqualTo(26);
        assertThat(resultCount).isEqualTo(2);
    }


    @Test
    @Transactional
    void ageTest2() {
        int resultCount = memberRepository.bulkAge(20);
        em.flush();
        em.clear();
        Member memberE = memberRepository.findByUsername("MemberE");
        // 26이 아닌걸 검증하고 싶어
        assertThat(memberE.getAge()).isNotEqualTo(26);
    }

    @Test
    @Transactional
    void ageTest3() { // 특정 나이 이상되면 그 리밋 걸기
        int limit = 20;
        int resultCount = memberRepository.bulkAgeLimit(limit);

        List<Member> memberList = memberRepository.findAll();
        Assertions.assertThat(resultCount).isEqualTo(2);
        memberList.forEach(member -> {
            System.out.println("member age = " + member.getAge());
            assertThat(member.getAge()).isLessThanOrEqualTo(limit);
        });
    }
}

