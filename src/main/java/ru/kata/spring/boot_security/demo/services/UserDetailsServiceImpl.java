package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private UserRepository userRepository;

        public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // переопределяем метод из UserDetailsService
    // по имени пользователя вернет самого пользователя
    @Override
    @Transactional // чтобы коллекция ролей подгрузилась нужно загнать все в одну транзакцию иначе при
    // залогинивании вылезает failed to lazily initialize a collection of role:
    // ru.tinyakov.security_training.entities.User.roles, could not initialize proxy - no Session
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username); //  если юзер в базе есть - мы его получим, если нет, то получим null
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username)); // юзер с таким именем не найден
        }
        return user;
    }

}
