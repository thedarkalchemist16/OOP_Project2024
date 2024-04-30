package com.example.twitterCloneAssgn.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(String resource) {
        String message = resource + " does not exist";
        Map<String, Object> body = new HashMap<>();
        body.put("error: ", message);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> UserSignUpException(String resource) {
        Map<String, Object> body = new HashMap<>();
        body.put("error: ", resource);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    public static class ApplicationException extends RuntimeException {
        public ApplicationException(String message) {
            super(message);
        }
    }

    public static class ResourceNotFoundException extends ApplicationException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public static class BadRequestException extends ApplicationException {
        public BadRequestException(String message) {
            super(message);
        }
    }

}
