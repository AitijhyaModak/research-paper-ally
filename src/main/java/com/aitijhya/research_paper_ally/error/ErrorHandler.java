package com.aitijhya.research_paper_ally.error;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorJson> handleNotFound(EntityNotFoundException e) {
        ErrorJson errorJson = buildResponse(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorJson);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorJson> handleDIVioloation(DataIntegrityViolationException e) {
        ErrorJson errorJson = buildResponse(HttpStatus.CONFLICT, "Database constraint violation.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorJson);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorJson> handleInvalidArguments(MethodArgumentNotValidException e) {
        ErrorJson errorJson = buildResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorJson);
    }

    private ErrorJson buildResponse(HttpStatus status, String message) {
        ErrorJson errorJson = new ErrorJson(
            status.value(),
            message,
            System.currentTimeMillis()
        );
        return errorJson;
    }
}
