package ru.skypro.socks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class SocksExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handleException(ResponseStatusException exception) {
        return new ResponseEntity<>(exception.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(ConstraintViolationException exception) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
