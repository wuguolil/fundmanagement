package com.example.position;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PositionNotFoundException extends IllegalArgumentException{
    private final int id;

    public PositionNotFoundException(int id) {
        super("Position with id " + id + " is not found!");
        this.id = id;
    }
}
