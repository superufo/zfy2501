package com.huadea.ext.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import  org.springframework.http.converter.HttpMessageNotReadableException;
import com.huadea.ext.commons.AjaxResult;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public AjaxResult handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return  AjaxResult.error("Invalid JSON format: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
