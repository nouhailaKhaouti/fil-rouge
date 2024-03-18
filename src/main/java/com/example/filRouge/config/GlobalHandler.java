package com.example.filRouge.config;


import com.example.filRouge.exception.AlreadyExistException;
import com.example.filRouge.exception.CustomException;
import com.example.filRouge.exception.DateValidationException;
import com.example.filRouge.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice

public class GlobalHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("error", errors);
        return errorResponse;
    }

    @ExceptionHandler(value= NotFoundException.class)
    public ResponseEntity<?> handleNotFoundErrors(NotFoundException e){
        return new ResponseEntity<>(e.getError(), e.getCode());
    }

    @ExceptionHandler(value= AlreadyExistException.class)
    public ResponseEntity<?> handleAlreadyExistErrors(AlreadyExistException e){
        return new ResponseEntity<>(e.getError(), e.getCode());
    }

    @ExceptionHandler(value= CustomException.class)
    public ResponseEntity<?> handleCustomErrors(CustomException e){
        return new ResponseEntity<>(e.getError(), e.getStatus());
    }

    @ExceptionHandler(value= DateValidationException.class)
    public ResponseEntity<?> handleDateValidationErrors(DateValidationException e){
        return new ResponseEntity<>(e.getError(), e.getCode());
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<?> handleLockedError(LockedException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    ProblemDetail handleAccessDeniedException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), ex.getMessage());
        problemDetail.setProperty("access_denied", "Unauthorized Failure");
        return problemDetail;
    }
    @ExceptionHandler({BadCredentialsException.class})
    ProblemDetail handleAuthenticationException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
        problemDetail.setProperty("bad_credentials", "Username or password is incorrect.");
        return problemDetail;
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    ProblemDetail handleInsufficientAuthenticationException(InsufficientAuthenticationException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
        problemDetail.setProperty("credentials", "Login credentials are missing.");
        return problemDetail;
    }

//    @ExceptionHandler(Exception.class)
//    ProblemDetail handleOtherException(Exception ex) {
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), ex.getMessage());
//        problemDetail.setProperty("server", "A server internal error occurs.");
//        return problemDetail;
//    }

    @ExceptionHandler(NoHandlerFoundException.class)
    ProblemDetail handleNoHandlerFoundException(NoHandlerFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), ex.getMessage());
        problemDetail.setProperty("API", "This API endpoint is not found.");
        return problemDetail;
    }
}
