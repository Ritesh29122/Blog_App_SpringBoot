package com.example.BLOG_APP.Exceptions;

import com.example.BLOG_APP.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        System.out.println("Handling exception: " + message); // Debug log
        ApiResponse apiResponse = new ApiResponse(message, false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
