package edu.business.services;

import edu.business.entities.Product;
import edu.crosscutting.exceptions.ProductNotFoundException;
import edu.business.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class DefaultProductService implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findProductById(Long id) {
        if (id == null || id < 0) {
            throw new IllegalArgumentException("product id cannot be neither null nor negative");
        }

        Product found = productRepository.findProductById(id);

        if (found == null) {
            throw new ProductNotFoundException(id);
        }

        return found;
    }

    public List<Product> findProductsContainingTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("product title cannot be neither null nor blank");
        }

        return productRepository.findProductsContainingTitle(title.trim());
    }

    public Long createProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("product cannot be null");
        }
        if (product.getId() != null) {
            throw new IllegalArgumentException("product with id " + product.getId() + " already exists");
        }

        productRepository.createProduct(product);

        return product.getId();
    }

}
