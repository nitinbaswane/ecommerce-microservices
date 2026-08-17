package com.ecomm.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<Map<String,Object>> handleExist(ResourceAlreadyExistException re){
        Map<String,Object> m=Map.of("error","conflict","message",re.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(m);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(ResourceNotFoundException re){
        Map<String,Object> m=Map.of("error","Not Found","message",re.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(m);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex){
        Map<String,Object> error=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err->error.put(err.getField(),err.getDefaultMessage()));
        Map<String,Object> body= Map.of("error","Validation Failed","message",error);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleAll(Exception ex){
        Map<String,Object> m=Map.of("error","Internal Server error","Message",ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(m);
    }



}
