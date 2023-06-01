package com.example.demowithtests.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class InputParameterExceptionHandler {

    @ExceptionHandler(InputParameterException.class)
    protected ResponseEntity<ErrorDetails> handleInputParameterException(WebRequest request) {
        return getErrorDetails("Bad request parameter", request);
    }

    @ExceptionHandler(NumberFormatException.class)
    protected ResponseEntity<ErrorDetails> handleNumberFormatException(WebRequest request) {
        return getErrorDetails("Number format exception", request);
    }

    private ResponseEntity<ErrorDetails> getErrorDetails(String message, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), message, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
