package com.turkcell.commonservice.config.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityAlreadyException.class)
    public ResponseEntity<ExceptionResponse> handleEntityAlreadyException(EntityAlreadyException e,
                                                                          HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST,
                        request.getServletPath(),
                        LocalDateTime.now(),
                        e.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleEntityNotFoundException(EntityNotFoundException e,
                                                                          HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value())
                .body(new ExceptionResponse(HttpStatus.BAD_REQUEST,
                        request.getServletPath(),
                        LocalDateTime.now(),
                        e.getMessage()));
    }
}
