package com.example.infofusionback.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {


    Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> handleAllExceptions(Throwable t) {

        logger.error(t.getMessage(), t);

        return new ResponseEntity<Object>(
                "Internal Server Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
