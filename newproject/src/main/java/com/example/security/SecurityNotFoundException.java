package com.example.security;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SecurityNotFoundException extends IllegalArgumentException{
    private final int id;

    public SecurityNotFoundException(int id) {
        super("Security with Id " + id + " is not found!");
        this.id = id;
    }
}
