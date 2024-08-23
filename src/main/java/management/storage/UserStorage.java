package management.storage;

import management.model.User;

import java.util.List;

public interface UserStorage {
    void add(User user);

    void update(User user);

    void delete(String email);

    List<User> findAll();

    User get(String email);
}
