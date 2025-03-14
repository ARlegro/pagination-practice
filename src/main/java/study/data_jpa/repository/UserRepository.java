package study.data_jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Window;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.data_jpa.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query("UPDATE User u SET u.point = 0")
    int resetPoint();


    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.point = 0")
    int resetPointAndClear();

    @Modifying
    @Query("UPDATE User u SET u.point = u.point + :point")
    int pointUp(@Param("point") int point);
}
