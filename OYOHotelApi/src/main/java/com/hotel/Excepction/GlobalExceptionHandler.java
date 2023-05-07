package com.hotel.Excepction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> myException(Exception se , WebRequest req){

        ErrorDetails med = new ErrorDetails();
        med.setTime(LocalDateTime.now());
        med.setMessage(se.getMessage());
        med.setDescription(req.getDescription(false));

        return new ResponseEntity<>(med, HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDetails> NoHandlerFound(NoHandlerFoundException se , WebRequest req){

        ErrorDetails med = new ErrorDetails();
        med.setTime(LocalDateTime.now());
        med.setMessage(se.getMessage());
        med.setDescription(req.getDescription(false));

        return new ResponseEntity<>(med, HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> myException(MethodArgumentNotValidException se , WebRequest req){

        ErrorDetails med = new ErrorDetails();
        med.setTime(LocalDateTime.now());
        med.setMessage(se.getMessage());
        med.setDescription(req.getDescription(false));

        return new ResponseEntity<>(med, HttpStatus.BAD_REQUEST) ;
    }

    @ExceptionHandler(OYOException.class)
    public ResponseEntity<ErrorDetails> myException(OYOException se , WebRequest req){

        ErrorDetails med = new ErrorDetails();
        med.setTime(LocalDateTime.now());
        med.setMessage(se.getMessage());
        med.setDescription(req.getDescription(false));

        return new ResponseEntity<>(med, HttpStatus.BAD_REQUEST) ;
    }
}
