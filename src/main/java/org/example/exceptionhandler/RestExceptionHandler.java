package org.example.exceptionhandler;

import jakarta.servlet.http.HttpServletRequest;
import org.example.exception.HabitNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // ИИшка считает что это хороший тон, дублировать эту инфу здесь
    public Map<String, Object> handleInvalidInput(MethodArgumentNotValidException ex, HttpServletRequest request){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("error", ex.getStatusCode().toString());
        errorDetails.put("message", "Недопустимый ввод");
        errorDetails.put("path", request.getRequestURI());
        errorDetails.put("method", request.getMethod());
        return errorDetails;
    }

    @ExceptionHandler(HabitNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleHabitNotFound(HabitNotFoundException ex, HttpServletRequest request){
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now());
        errorDetails.put("message", ex.getMessage());
        errorDetails.put("path", request.getRequestURI());
        errorDetails.put("method", request.getMethod());
        return errorDetails;
    }
}
