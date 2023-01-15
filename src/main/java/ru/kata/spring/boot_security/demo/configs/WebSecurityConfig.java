package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.Service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, SuccessUserHandler successUserHandler) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.successUserHandler = successUserHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/users_page/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin().successHandler(successUserHandler)// стандартная форма для логина
                .and()
                .logout().logoutSuccessUrl("/"); //  при успешеном разлогинивании выйти в корень сайта
               //.csrf().disable(); // отключаем защиту от межсайтовой подделки запросов Cross-Site Request Forgery
                // для стандартной формы логина она по умолчанию включена

    }

    // Кодировщик паролей
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    // Аутентификация с помощью DaoAuthenticationProvider
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // указываем провайдеру кодировщик паролей
        authenticationProvider.setUserDetailsService(userDetailsServiceImpl);  //  предоставляет провайдеру юзеров
        return authenticationProvider;
    }


}

//    // аутентификация inMemory
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build();
//        UserDetails admin =
//                User.withDefaultPasswordEncoder()
//                        .username("admin")
//                        .password("admin")
//                        .roles("ADMIN")
//                        .build();
//        return new InMemoryUserDetailsManager(user, admin);
//    }