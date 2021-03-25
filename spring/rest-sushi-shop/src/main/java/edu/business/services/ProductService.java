package edu.business.services;

import edu.business.entities.Product;

import java.util.List;

public interface ProductService {

    Product findProductById(Long id);

    List<Product> findProductsContainingTitle(String title);

    Product saveProduct(Product product);

}
