package study.data_jpa.paging;

import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import study.data_jpa.entity.Member;
import study.data_jpa.entity.Team;
import study.data_jpa.repository.MemberRepository;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MemberPaging {

    @Autowired
    MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            memberRepository.save(new Member("member" + i, 20 + i));
        }
    }

    @Test
    void paging() {
        memberRepository.save(new Member("memberA", 20));
        memberRepository.save(new Member("memberB", 20));
        memberRepository.save(new Member("memberC", 20));
        memberRepository.save(new Member("memberD", 20));
        memberRepository.save(new Member("memberE", 20));

        int age = 20;

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
        // PageRequest : 페이지를 요청하는 객체. Pageable 인터페이스를 구현한 클래스
        Page<Member> page = memberRepository.findPageByAge(age, pageRequest);
        // Page 타입으로 반환할 시 TotalCount 쿼리까지 자동으로 날려준다.(쿼리 2번 실행됨)

        page.getContent().forEach((member) -> {
            System.out.println("member = " + member);
        });

        assertThat(page.getTotalElements()).isEqualTo(6);
        assertThat(page.getTotalPages()).isEqualTo(2);
    }

    @Test
    void slice() {
        memberRepository.save(new Member("memberA", 20));
        memberRepository.save(new Member("memberB", 20));
        memberRepository.save(new Member("memberC", 20));
        memberRepository.save(new Member("memberD", 20));
        memberRepository.save(new Member("memberE", 20));

        int age = 20;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
        Slice<Member> slice = memberRepository.findSliceByAge(age, pageRequest);
        slice.getContent().forEach((member) -> {
            System.out.println("member = " + member);
            //member = Member(id=105, username=memberE, age=20)
            //member = Member(id=104, username=memberD, age=20)
            //member = Member(id=103, username=memberC, age=20)
        });
        System.out.println("slice.getSize() = " + slice.getSize());
        System.out.println("slice.hasNext() = " + slice.hasNext());
        System.out.println("slice.getPageable() = " + slice.getPageable());
        //slice.getSize() = 3
        //slice.hasNext() = true
        //slice.getPageable() = Page request [number: 0, size 3, sort: username: DESC]


    }

    @Test
    void detachQuery(){
        memberRepository.save(new Member("memberA", 20));
        memberRepository.save(new Member("memberB", 20));
        memberRepository.save(new Member("memberC", 20));
        memberRepository.save(new Member("memberD", 20));
        memberRepository.save(new Member("memberE", 20));

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
        Page<Member> detachQueryByAge = memberRepository.findDetachQueryByAge(20, pageRequest);
    }
}
