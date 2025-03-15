package study.data_jpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.data_jpa.entity.Member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 이유 : 컨테이너를 사용하기 위해
@Transactional
// JPA의 EntityManager의 모든 변경은 기본적으로 트랜잭션 안에서 실행되어야 한다.
// 테스트는 트랜잭션을 사용하여 DB에 반영하지 않고 롤백한다. => flush()를 호출하지 않음
// @Rollback(false) // 롤백을 하지 않으려면 이 어노테이션을 사용한다.
class MemberJpaRepositoryTest {

    @Autowired
    MemberJpaRepository memberJpaRepository;

//    @Test
//    public void testMember(){
//        Member memberA = new Member("memberA");
//        Member savedMember = memberJpaRepository.save(memberA);
//
//        Member findMember = memberJpaRepository.find(savedMember.getId());
//        assertThat(findMember.getId()).isEqualTo(savedMember.getId());
//        assertThat(findMember.getUsername()).isEqualTo(savedMember.getUsername());
//        assertThat(findMember).isEqualTo(savedMember);
//    }
}