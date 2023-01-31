package ru.kata.spring.boot_security.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Выносим обработку исключений в отдельный класс
@ControllerAdvice
public class UserGlobalExceptionHandler {

    // Отвечает за PersonNotFoundException исключения
    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(PersonNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(
                "Такой пользователь не найден", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    // Отвечает за все остальные исключения
    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(Exception e) {
        UserErrorResponse response = new UserErrorResponse(
                "Введите корректный id", System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
