package study.data_jpa.bulk;

import jakarta.annotation.PostConstruct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.data_jpa.entity.User;
import study.data_jpa.repository.UserRepository;

import java.util.List;

@SpringBootTest
@Rollback(false)
public class UserPoint {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    void init(){
        userRepository.deleteAll();
        userRepository.save(new User("userA", 10));
        userRepository.save(new User("userB", 20));
        userRepository.save(new User("userC", 30));
        userRepository.save(new User("userD", 40));
        userRepository.save(new User("userE", 50));
    }

    @Test
    @Transactional
    void addPointWithoutBulk() {
        List<User> memberList = userRepository.findAll();
        memberList.forEach(user -> {
            user.addPoint(10);
        });
        // 업데이트 쿼리 5개 날라감
    }

    @Test
    @Transactional
    void addPointWithBulk() { // 예시 : 포인트를 100씩 올려주는 쿼리
        int bulkPoint = 10;
        int resultCount = userRepository.pointUp(100);
        Assertions.assertThat(resultCount).isEqualTo(5);
    }

    @Test
    @Transactional
    void resetPointWithBulk(){
        List<User> beforeBulkUsers = userRepository.findAll();
        //int resetCount = userRepository.resetPoint();
        int resetCount = userRepository.resetPointAndClear();
        System.out.println("resetCount = " + resetCount);
        beforeBulkUsers.forEach(user -> {
            System.out.println("user.getPoint() = " + user.getPoint());
        });
        System.out.println("====================================");
        userRepository.findAll().forEach(user -> {
            System.out.println("user.getPoint() = " + user.getPoint());
        });


    }

}
