package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user JOIN FETCH user.roles WHERE user.username = :username")
// почему то без этого не работает
    User findByUsername(String username); // JpaRepository распарсит имя метода и поймет, что мы хотим найти юзера по username

    void deleteById(Long Id);

    @Override
    List<User> findAll();

    User save(User user);


}
