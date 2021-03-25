package edu.crosscutting.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long productID) {
        super("No product with id = " + productID + " could be found");
    }

}
