package edu.domain.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long productID) {
        super("No product with id = " + productID + " could be found");
    }

}
