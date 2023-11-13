package ru.skypro.exсeption;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
@RestControllerAdvice
public class SocksStoreExceptionHandler     {
    @ExceptionHandler
    public ResponseEntity<?> handleException(ResponseStatusException exception) {
        return new ResponseEntity<>(exception.getStatusCode());
    }

    @ExceptionHandler
    public ResponseEntity<?> handleException(ConstraintViolationException exception) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
