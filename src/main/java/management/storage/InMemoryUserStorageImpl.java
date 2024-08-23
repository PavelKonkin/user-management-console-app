package management.storage;

import management.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryUserStorageImpl implements UserStorage{
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void add(User user) {
        users.put(user.getEmail(), user);
    }

    @Override
    public void update(User user) {
        users.put(user.getEmail(), user);
    }

    @Override
    public void delete(String email) {
        users.remove(email);
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(users.values());
    }

    @Override
    public User get(String email) {
        return users.get(email);
    }
}
