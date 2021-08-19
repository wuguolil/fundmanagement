package com.example.manager;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ManagerNotFoundException extends IllegalArgumentException{
    private final int managerId;

    public ManagerNotFoundException(int managerId) {
        super("Manager with id " + managerId + " is not found.");
        this.managerId = managerId;
    }

}
