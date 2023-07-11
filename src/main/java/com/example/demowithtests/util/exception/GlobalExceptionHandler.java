package com.example.demowithtests.util.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.StringJoiner;

import static com.example.demowithtests.util.exception.ErrorDetails.getResponseEntity;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(WebRequest request) {
        StringJoiner sj = new StringJoiner("; ");
        request.getParameterMap().forEach((key, value) -> {
            String values = String.join(",", value);
            sj.add(key + "=" + values);
        });
        String message = "Entity not found with parameters: " + sj;
        return getResponseEntity(message, request, HttpStatus.NOT_FOUND);
    }
}
