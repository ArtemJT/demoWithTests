package com.example.demowithtests.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class PassportHandedException extends RuntimeException {
    public PassportHandedException() {
        super();
    }

    public PassportHandedException(String message) {
        super(message);
    }
}
