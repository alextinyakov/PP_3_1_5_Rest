package ru.kata.spring.boot_security.demo.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;


import java.util.List;

public interface UserService {
    void add(User user, Long[] roles);
  //  void add(User user);
    List<User> index();
    void delete(Long id);

    User findById(Long id);
}
