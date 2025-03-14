package study.data_jpa.dto;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DtoTransfer {

    private final UserMapper userMapper;

    @GetMapping("/user")
    public UserDto toDto() {
        User user = new User("test 이름", "test 이메일", "test 비밀번호");
        return userMapper.toDto(user);
    }
}
