package service.jcf;

import entity.User;
import service.UserService;
import java.util.ArrayList;
import java.util.List;

public class JCFUserService implements UserService {
    private final List<User> data = new ArrayList<>();

    @Override
    public boolean addUser(User user) {
        return data.add(user);
    }

    @Override
    public User findById(String id) {
        return data.stream()
                .filter(user -> user.getId().toString().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(data);
    }

    @Override
    public boolean updateUser(User user) {
        return true;
    }

    @Override
    public boolean withdraw(String id) {
        return data.removeIf(user -> user.getId().toString().equals(id));
    }

}