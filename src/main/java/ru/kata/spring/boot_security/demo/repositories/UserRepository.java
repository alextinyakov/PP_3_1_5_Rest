package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Optional;

// Применяем Spring Data JPA.  Паттерн DAO теперь не нужен, переходим на паттерн Repository
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user JOIN FETCH user.roles WHERE user.username = :username")// почему то без этого не работает
    User findByUsername(String username); // JpaRepository распарсит имя метода и поймет, что мы хотим найти юзера по username


}
