package management.service;

import management.model.dto.UserDto;

public interface UserService {
    void create(UserDto userDto);
    void update(UserDto userDto);
    void delete(String email);
    UserDto get(String email);
}
