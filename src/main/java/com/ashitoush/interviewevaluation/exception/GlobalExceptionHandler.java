package com.ashitoush.interviewevaluation.exception;

import com.ashitoush.interviewevaluation.response.GlobalApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<GlobalApiResponse> appExceptionHandler(AppException exception) {
        GlobalApiResponse response = GlobalApiResponse.builder()
                .status(false)
                .message(exception.getMessage())
                .data(null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
