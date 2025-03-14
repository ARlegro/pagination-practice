package study.data_jpa.repository;

import jakarta.annotation.PostConstruct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.data_jpa.entity.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberQueryMethod {

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
    void test1(){
        List<Member> memberByQuery = memberRepository.findMemberByQuery(50);
        memberByQuery.forEach((member)->{
            assertThat(member.getAge()).isGreaterThan(50);
        });
    }

    @Test // 값조회 테스트
    void valueFindTest(){
        List<String> usernames = memberRepository.findUsernames();
        usernames.forEach((username)->{
            System.out.println("username = " + username);
        });
    }

    @Test
    void test3(){
        List<String> memberNameList = List.of("하늘", "초롱", "혜연", "지수");

        memberNameList.forEach((name)-> memberRepository.save(new Member(name)));
        List<Member> memberByList = memberRepository.findMemberByList(memberNameList);
        memberByList.forEach((member)->{
            System.out.println("member = " + member);
            assertThat(memberNameList).contains(member.getUsername());
        });
    }

    @Test
    void optionalTest(){
        memberRepository.save(new Member("DDD"));
        Optional<Member> ddd = memberRepository.findOptionalByUsername("DDD");
        Optional<Member> ccc = memberRepository.findOptionalByUsername("CCC");

        assertThat(ddd).isPresent();
        assertThatThrownBy(() -> ccc.orElseThrow(IllegalArgumentException::new)).isInstanceOf(Exception.class);
        // 예외가 터져야 정상 처리되도록 하는 assert
    }
}
