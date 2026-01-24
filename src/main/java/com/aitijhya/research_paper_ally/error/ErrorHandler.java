package com.aitijhya.research_paper_ally.error;

import com.aitijhya.research_paper_ally.exception.NotAuthorizedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aitijhya.research_paper_ally.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {
   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<ErrorJson> handleNotFound(EntityNotFoundException e) {
      ErrorJson errorJson = buildResponse(HttpStatus.NOT_FOUND, e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorJson);
   }

   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<ErrorJson> handleResourceNotFound(ResourceNotFoundException e) {
      ErrorJson errorJson = buildResponse(HttpStatus.NOT_FOUND, e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorJson);
   }

   @ExceptionHandler(DataIntegrityViolationException.class)
   public ResponseEntity<ErrorJson> handleDIViolation(DataIntegrityViolationException e) {
      ErrorJson errorJson = buildResponse(HttpStatus.CONFLICT, "Database constraint violation.");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(errorJson);
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ErrorJson> handleInvalidArguments(MethodArgumentNotValidException e) {
      ErrorJson errorJson = buildResponse(HttpStatus.BAD_REQUEST, "Bad Request");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorJson);
   }

   @ExceptionHandler(NotAuthorizedException.class)
   public ResponseEntity<ErrorJson> handleNotAuthorized(NotAuthorizedException e) {
      ErrorJson errorJson = buildResponse(HttpStatus.FORBIDDEN, "Unauthorized");
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorJson);
   }

   private ErrorJson buildResponse(HttpStatus status, String message) {
      return new ErrorJson(
            status.value(),
            message,
            System.currentTimeMillis());
   }
}
