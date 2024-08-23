package management.mapper;

import management.model.User;
import management.model.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User convertUserDto(UserDto userDto) {
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .roles(userDto.getRoles())
                .phones(userDto.getPhones())
                .build();
    }

    public UserDto convertUser(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .phones(user.getPhones())
                .build();
    }
}
