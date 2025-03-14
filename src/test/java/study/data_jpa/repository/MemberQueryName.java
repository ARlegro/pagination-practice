package study.data_jpa.repository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.data_jpa.entity.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberQueryName {

    @Autowired
    MemberRepository memberRepository;
    // 미리 시작할 것
    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            memberRepository.save(new Member("member" + i, 20 + i));
        }
    }

    @Test
    void postTest(){
        List<Member> findMemberList = memberRepository.findAll();
        assertThat(findMemberList).hasSize(100);
    }

    @Test
    void andTest(){
        List<Member> memberLess20 = memberRepository.findByUsernameAndAgeLessThan("member1", 20);
        List<Member> memberGreater20 = memberRepository.findByUsernameAndAgeGreaterThan("member1", 20);

        assertThat(memberLess20).hasSize(0);
        assertThat(memberGreater20).hasSize(2);
    }

    @Test
    void countTest(){
        List<Member> allMember = memberRepository.findAll();
        assertThat(allMember).hasSize(100);

        int nameCount = memberRepository.countByUsername("member50");
        int betweenCount = memberRepository.countByAgeBetween(20, 30);
        assertThat(nameCount).isEqualTo(1);
        System.out.println("betweenCount = " + betweenCount);
        //betweenCount = 11
        assertThat(betweenCount).isEqualTo(11);
    }

    @Test
    void existTest() {
        boolean isTrue100 = memberRepository.existsById(100L);
        boolean isTrue101 = memberRepository.existsById(101L);

        assertThat(isTrue100).isTrue();
        assertThat(isTrue101).isFalse();

        // AGE가 20 ~ 119인 MEMBER들이 있는 상황
        boolean isTrueGreater120 = memberRepository.existsMemberByAgeIsGreaterThan(120);
        boolean isTrueGreater119 = memberRepository.existsMemberByAgeIsGreaterThan(119);
        boolean isTrueGreater118 = memberRepository.existsMemberByAgeIsGreaterThan(118);
        assertThat(isTrueGreater120).isFalse();
        assertThat(isTrueGreater119).isFalse(); // 119은 포함 안됨
        assertThat(isTrueGreater118).isTrue();
    }

    @Test
    @Transactional
    // 원래 : 엔티티를 삭제하는 JPA 메서드는 트랜잭션이 있어야 한다.
    // 근데 단순 deleteById()는 트랜잭션이 없어도 Spring Data JPA에서 기본적으로 트랜잭션을 생성해준다.
    // 근데, deleteByUsername() 같은 사용자 정의 메서드는 트랜잭션이 있어야 한다.
    void deleteTest(){
        // memberRepository.deleteAll(); 이건 반환값이 없다
        memberRepository.deleteById(2L);
        memberRepository.deleteById(3L);
        int deletedMemberCount = memberRepository.deleteByUsername("member7");
        assertThat(deletedMemberCount).isEqualTo(1);
    }

    @Test
    void distinctTest(){
        for (int i = 0; i < 20; i++) {
            memberRepository.save(new Member("중복된 멤버", 22222));
        }

        int duplicatedMember = memberRepository.countDistinctByUsername("중복된 멤버");
        assertThat(duplicatedMember).isEqualTo(20);

        memberRepository.countDistinctByUsername("중복된 멤버");
        int duplicatedMemberCount = memberRepository.findDistinctByUsername("중복된 멤버");
        assertThat(duplicatedMemberCount).isEqualTo(20);
    }

    @Test
    void limitTest(){
        // List<Member> top3Member = memberRepository.findTOP3ByOrderByAgeDesc();
        // 잘못된 점 TOP3는 대문자여야 한다.
        List<Member> top3Member = memberRepository.findTop3ByOrderByAgeDesc();
        top3Member.forEach(System.out::println);
        assertThat(top3Member).hasSize(3);
    }

    @Test
    @Transactional
    void findFirstTest(){
        // 100명의 member가 등록된 상황
        Member oldestMember = memberRepository.findFirstByOrderByAgeDesc();
        Member lastMember = memberRepository.findById(100L).get();
        assertThat(oldestMember).isEqualTo(lastMember);
    }
}
