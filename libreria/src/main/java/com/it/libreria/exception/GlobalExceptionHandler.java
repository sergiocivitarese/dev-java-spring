package com.it.libreria.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
//permette la gestione globale delle eccezioni
public class GlobalExceptionHandler {

    //implementazione del metodo build response
    // Metodo di utilità per costruire la risposta
    private ResponseEntity<Object> buildResponse(HttpStatus status, String message, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", request.getDescription(false).replace("uri=",""));
        return new ResponseEntity<>(body,status);
    }

    //ExceptionHandler(..) intercetta un tipo specifico di errore che indichiamo nelle parentesi
    //l'errore deve essere inserito con il .class ad esempio : IllegalArgumentException.class
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntitynotFound(EntityNotFoundException ex, WebRequest request) {
        return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request); //404 not found
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request); //400 bad request, parametri non validi
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalState(IllegalStateException ex, WebRequest request) {
        return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request);  //409 conflict, stato non valido
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneric(Exception ex, WebRequest request) {
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Errore imprevisto", request); //generic
    }


}
