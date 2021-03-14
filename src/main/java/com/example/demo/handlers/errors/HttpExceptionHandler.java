package com.example.demo.handlers.errors;

import com.example.demo.handlers.errors.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleConstrainViolationException(ConstraintViolationException ex) {
        ErrorDTO errorDTO = new ErrorDTO("Some of the fields were provided incorrectly.", HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.getFieldErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorDTO errorDTO = new ErrorDTO(ex.getMessage(), HttpStatus.NOT_FOUND.name());
        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }

}
