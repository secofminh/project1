package com.example.project1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ProductException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;
}
