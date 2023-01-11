package ru.kata.spring.boot_security.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)  // для всех операций стоит readonly кроме помеченных @Transactional
class UserServiceImpl implements UserService {
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;// = new BCryptPasswordEncoder();


    @Autowired
    UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> index() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void add(User user, Long[] roleId) {
        user.setRoles(roleService.getSetRoles(roleId));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
// добавляет только юзера без роли
//    @Override
//    @Transactional
//    public void add(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        Optional<User> foundUser =  userRepository.findById(id);
        if (foundUser == null) {
            throw new UsernameNotFoundException(String.format("User with id  '%s' not found", id));
        }
            return foundUser.orElse(null);
    }


}
