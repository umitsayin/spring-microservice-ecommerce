package com.turkcell.documentservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DocumentServiceExceptionHandler {

    @ExceptionHandler(MediaNotFoundException.class)
    public ResponseEntity<?> handleMediaNotFoundException(MediaNotFoundException ex, HttpServletRequest request){
        return ResponseEntity.ok(getExceptionDTO(ex,request));
    }

    @ExceptionHandler(InvalidMediaTypeException.class)
    public ResponseEntity<?> handleInvalidMediaTypeException(InvalidMediaTypeException ex, HttpServletRequest request){
        return ResponseEntity.ok(getExceptionDTO(ex,request));
    }

    @ExceptionHandler(MediaTransferException.class)
    public ResponseEntity<?> handleMediaTransferException(MediaTransferException ex, HttpServletRequest request){
        return ResponseEntity.ok(getExceptionDTO(ex,request));
    }

    private Map<String,String>getExceptionDTO(RuntimeException ex, HttpServletRequest request){
        Map<String, String> exception = new HashMap<>();

        exception.put("status", HttpStatus.BAD_REQUEST.toString());
        exception.put("path", request.getServletPath());
        exception.put("time", LocalDateTime.now().toString());
        exception.put("message", ex.getMessage());

        return exception;
    }

}
