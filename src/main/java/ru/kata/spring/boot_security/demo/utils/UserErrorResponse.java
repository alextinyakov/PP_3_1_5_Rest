package ru.kata.spring.boot_security.demo.utils;

import lombok.Data;

@Data
public class UserErrorResponse {

    private String message;
    private Long timestamp;

    public UserErrorResponse(String message, Long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
