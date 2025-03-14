package study.data_jpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.data_jpa.entity.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testMember() {
        Member memberA = new Member("memberA");
        Member savedMember = memberRepository.save(memberA);

        // 옵셔널 반환
        Optional<Member> byId = memberRepository.findById(memberA.getId());
        Member findedMember = byId.get();
        assertThat(findedMember.getId()).isEqualTo(savedMember.getId());
        assertThat(findedMember.getUsername()).isEqualTo(savedMember.getUsername());
        assertThat(findedMember).isEqualTo(savedMember);

    }

    @Test
    public void testMember2() {
        Member memberA = new Member("memberA", 12);
        Member memberB = new Member("memberB", 20);
        Member memberC = new Member("memberC", 25);
        memberRepository.save(memberA);
        memberRepository.save(memberB);
        memberRepository.save(memberC);

        List<Member> testA = memberRepository.findByUsernameAndAgeGreaterThan("memberA", 15);
        List<Member> testB = memberRepository.findByUsernameAndAgeGreaterThan("memberA", 10);
        assertThat(testA.size()).isEqualTo(0);
        assertThat(testB.size()).isEqualTo(1);
        assertThat(testB.get(0).getUsername()).isEqualTo("memberA");
    }
}
