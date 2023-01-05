package ru.kata.spring.boot_security.demo.Service;
import ru.kata.spring.boot_security.demo.models.User;


import java.util.List;

public interface UserService {
    void add(User user);
    List<User> index();
    User showById(Long id);
    void update(User updatedUser);
    void delete(Long id);
}
