package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Set;

// Применяем Spring Data JPA.  Паттерн DAO теперь не нужен, переходим на паттерн Repository
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByIdIn(Long[] roleId);
}
