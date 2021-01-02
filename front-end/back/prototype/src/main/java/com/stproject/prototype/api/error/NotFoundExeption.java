package com.stproject.prototype.api.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundExeption extends RuntimeException{
    public NotFoundExeption(int id) {

        super("no element for id: " + id);
    }
}
