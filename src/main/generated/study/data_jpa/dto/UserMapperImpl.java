package study.data_jpa.dto;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        String name = null;
        String email = null;

        name = user.getName();
        email = user.getEmail();

        UserDto userDto = new UserDto( name, email );

        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        String name = null;
        String email = null;

        name = userDto.name();
        email = userDto.email();

        String password = null;

        User user = new User( name, email, password );

        return user;
    }
}
