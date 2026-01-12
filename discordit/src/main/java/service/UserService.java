package service;

import entity.User;
import java.util.List;

public interface UserService {
    boolean addUser(User user);

    User findById(String id);

    List<User> findAll();

    boolean updateUser(User user);

    boolean withdraw(String id);
}