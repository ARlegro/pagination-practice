package study.data_jpa.auditing;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.data_jpa.entity.Member;
import study.data_jpa.repository.MemberRepository;

import java.util.List;

import static java.lang.Thread.sleep;

@SpringBootTest
public class MemberTest {


    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void jpaEventListener() throws InterruptedException {
        Member memberA = new Member("memberA", 10);
        memberRepository.save(memberA);

        sleep(1000);
        memberA.setAge(20);
        em.flush();
        em.clear();

        Member findMember = memberRepository.findAll().get(0);
        System.out.println("찾은 멤버 = " + findMember);
        System.out.println("findMember.getCreatedAt() = " + findMember.getCreatedAt());
        System.out.println("findMember.getUpdatedAt() = " + findMember.getUpdatedAt());
        System.out.println("findMember.getCreatedBy() = " + findMember.getCreatedBy());
        System.out.println("findMember.getUpdatedBy() = " + findMember.getUpdatedBy());
    }

}
