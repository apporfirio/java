package edu.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class JsonResponseController {

    public <T> ResponseEntity<T> ok(T responseBody) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(responseBody);
    }

    public <T> ResponseEntity<T> badRequest(T responseBody) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(responseBody);
    }

    public <T> ResponseEntity<T> notFound(T responseBody) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(responseBody);
    }

}
