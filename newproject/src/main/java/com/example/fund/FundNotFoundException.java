package com.example.fund;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FundNotFoundException extends IllegalArgumentException{

    private final int id;

    public FundNotFoundException(int id) {
        super("Fund with id " + id +" is not found!");
        this.id = id;
    }

}
