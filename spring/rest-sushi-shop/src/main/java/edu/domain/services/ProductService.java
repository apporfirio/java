package edu.domain.services;

import edu.domain.entities.Product;
import edu.domain.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findProductByID(Long id) {
        return productRepository.findProductByID(id);
    }
}
