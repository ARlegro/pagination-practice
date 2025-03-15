package study.data_jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.data_jpa.entity.Member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
    List<Member> findByUsernameAndAgeLessThan(String username, int age);


    int countByUsername(String member50);

    int countByAgeBetween(int i1, int i2);

    boolean existsMemberByAgeIsGreaterThan(int ageIsGreaterThan);

    int deleteByUsername(String member4);

    int countDistinctByUsername(String username);

    int findDistinctByUsername(String username);

    List<Member> findFirstByAge(int age);

    List<Member> findTopByAge(int age);

    List<Member> findTop3ByAge(int age);
    List<Member> findTop3ByOrderByAgeDesc();

    Member findFirstByOrderByAgeDesc();

    @Query("SELECT m FROM Member m Where m.age > :age")
    List<Member> findMemberByQuery(@Param("age") int age);

    @Query("SELECT m.username FROM Member m")
    List<String> findUsernames();

    @Query("SELECT m FROM Member m where m.username in :memberNameList")
    List<Member> findMemberByList(@Param("memberNameList") List<String> names);

    Optional<Member> findOptionalByUsername(String username);

    Page<Member> findPageByAge(int age, Pageable pageable);
    Slice<Member> findSliceByAge(int age, Pageable pageable);

//    @Query(value = "SELECT m FROM Member m left join Team t WHERE m.age = :age",
//            countQuery = "SELECT count(m) FROM Member m WHERE m.age = :age")
//    Page<Member> findDetachQueryByAge(@Param("age") int age, Pageable pageable);

    @Query(value = "SELECT m FROM Member m left join m.team t WHERE m.age = :age")
    Page<Member> findDetachQueryByAge(@Param("age") int age, Pageable pageable);

    //@Modifying(flushAutomatically = true)
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.age = m.age * 888 WHERE m.age > :age")
    int bulkAge(@Param("age") int age);

    Member findByUsername(String username);

    @Override
    @EntityGraph(attributePaths = "team")
    List<Member> findAll();

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.age = :age WHERE m.age > :age")
    int bulkAgeLimit(@Param("age") int age);

    Optional<Member> findById(UUID id);
}



















