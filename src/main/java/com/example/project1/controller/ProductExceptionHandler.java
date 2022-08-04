package com.example.project1.controller;

import com.example.project1.exception.ProductException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler(ProductException.class)
    protected ResponseEntity<?> handleProductException(ProductException e) {
        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

}
