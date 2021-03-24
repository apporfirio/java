package edu.domain.repositories.interfaces;

import edu.domain.entities.Product;

import java.util.List;

public interface ProductRepository {

    Product findProductById(Long id);

    List<Product> findProductsContainingTitle(String title);

    Product saveProduct(Product product);

}
