package study.data_jpa.entityGraph;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.data_jpa.entity.Member;
import study.data_jpa.entity.Team;
import study.data_jpa.repository.MemberRepository;
import study.data_jpa.repository.TeamRepository;

@SpringBootTest
public class entityGraph {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void entityGraphTest(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member memberA = memberRepository.save(new Member("memberA", 10));
        Member memberB = memberRepository.save(new Member("memberB", 20));
        Member memberC = memberRepository.save(new Member("memberC", 30));
        Member memberD = memberRepository.save(new Member("memberD", 40));
        Member memberE = memberRepository.save(new Member("memberE", 50));
        memberA.setTeam(teamA);
        memberB.setTeam(teamA);
        memberC.setTeam(teamB);
        memberD.setTeam(teamB);
        em.flush();
        em.clear();

        System.out.println("====================================");
        memberRepository.findAll().forEach(member -> {
                    System.out.println("member = " + member);
                    System.out.println("member.getTeam() = " + member.getTeam());
                });
        System.out.println("====================================");
    }

    @Test
    @Transactional
    void collectionEntityTest(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member memberA = memberRepository.save(new Member("memberA", 10));
        Member memberB = memberRepository.save(new Member("memberB", 20));
        Member memberC = memberRepository.save(new Member("memberC", 30));
        Member memberD = memberRepository.save(new Member("memberD", 40));
        memberA.setTeam(teamA);
        memberB.setTeam(teamA);
        memberC.setTeam(teamB);
        memberD.setTeam(teamB);

        em.flush();
        em.clear();

        System.out.println("====================================");
        teamRepository.findAll().forEach(team -> {
            System.out.println("team = " + team);
            System.out.println("team.getMembers() = " + team.getMembers());
            //System.out.println("team.getMembers() = " + team.getMembers());
        });

    }

}
