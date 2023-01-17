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
//    @Autowired
//    public void setUserRepository(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
    // обертка над методом из интерфейса UserRepository
    // метод удален по замечанию ментора
//    public User findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }

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

    //  С этой херней из видео почему то не работает, ошибка 500.
//        new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                mapRolesToAuthorities(user.getRoles()));


//    // получаем коллекцию авторитис(прав доступа) из коллекции ролей и передаем в loadUserByUsername
//    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
//        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
//    }
}
