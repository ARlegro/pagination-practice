package study.data_jpa.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.data_jpa.entity.Member;
import study.data_jpa.repository.MemberRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members")
    public Page<Member> getMembers(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }

    @GetMapping("/api/membersV1")
    public List<MemberDto> apiMembers(Pageable pageable) {
        Page<Member> memberPage = memberRepository.findAll(pageable);
//        Page<MemberDto> dtoPage = memberPage.map(member ->
//                MemberDto.toDto(member.getId(), member.getUsername(), member.getAge()));
        return memberPage.map(MemberDto::new).getContent();
    }


    @GetMapping("/api/membersV2")
    public List<MemberDto> apiV2Members(@PageableDefault(value = 3, size = 10, sort = "username", direction = Sort.Direction.DESC)
                                            Pageable pageable) {
        Page<Member> memberPage = memberRepository.findAll(pageable);
        return memberPage.map(MemberDto::new).getContent();
    }


    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            memberRepository.save(new Member("member" + i, 10 + i));
        }
    }
}
