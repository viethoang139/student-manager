package com.leviethoang.advice;

import com.leviethoang.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandleException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handelMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String,String> error = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(errorMap ->
                        error.put(errorMap.getField(),
                                errorMap.getDefaultMessage()));
        return error;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(StudentNotFoundException.class)
    public Map<String,String> handelStudentNotFoundException(StudentNotFoundException ex){
        Map<String,String> error = new HashMap<>();
        error.put("error" , ex.getMessage());
        return error;
    }

}
