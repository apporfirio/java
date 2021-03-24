package edu.controllers.advices;

import edu.domain.entities.Product;
import edu.domain.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductAdvice {
    //
    // Todos os tratamentos definidos em um @ControllerAdvice são globais.
    //

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Product> handleProductNotFoundException() {
        return ResponseEntity.notFound().build();
    }
}
