package com.proger4.webproger4.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);
    @ExceptionHandler(value = {RuntimeException.class, IllegalArgumentException.class})
    public ResponseEntity<String> handleException(Exception ex) {
        log.error("Error occurred: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}