package com.turkcell.commonservice.config.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionResponse(HttpStatus status, String path, LocalDateTime timestamp, String message) {
}
