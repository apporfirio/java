package edu.business.repositories;

import edu.business.entities.Product;

import java.util.List;

public interface ProductRepository {

    Product findProductById(Long id);

    List<Product> findProductsContainingTitle(String title);

    void createProduct(Product product);
}
