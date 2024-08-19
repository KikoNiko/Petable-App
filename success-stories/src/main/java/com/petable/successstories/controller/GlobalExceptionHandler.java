package com.petable.successstories.controller;

import com.petable.successstories.exception.StoryNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StoryNotFoundException.class)
    public ResponseEntity<?> handleStoryNotFound() {
        return ResponseEntity.notFound().build();
    }
}
