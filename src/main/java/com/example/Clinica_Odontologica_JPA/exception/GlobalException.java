package com.example.Clinica_Odontologica_JPA.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<String> tratamiendoResourceNotFoundException(ResourceNotFoundException rnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" mensaje: "+rnfe.getMessage());
    }
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<String> tratamiendoBadRequestException(BadRequestException rnfe){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" mensaje: "+rnfe.getMessage());
    }
}
