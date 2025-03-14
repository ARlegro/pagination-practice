package study.data_jpa.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import study.data_jpa.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Override
    @EntityGraph(attributePaths = {"members"})
    List<Team> findAll();
}
