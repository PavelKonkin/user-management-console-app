package management.service;

import management.mapper.UserMapper;
import management.model.User;
import management.model.dto.UserDto;
import management.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserStorage userStorage;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserStorage userStorage, UserMapper userMapper) {
        this.userStorage = userStorage;
        this.userMapper = userMapper;
    }
    @Override
    public void create(UserDto userDto) {
        User user = userMapper.convertUserDto(userDto);
        userStorage.add(user);
    }

    @Override
    public void update(UserDto userDto) {
        userStorage.update(userMapper.convertUserDto(userDto));
    }

    @Override
    public void delete(String email) {
        userStorage.delete(email);
    }

    @Override
    public UserDto get(String email) {
        User user = userStorage.get(email);
        if (user != null) {
            return userMapper.convertUser(userStorage.get(email));
        } else {
            return null;
        }
    }
}
