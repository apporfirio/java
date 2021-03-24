package edu.domain.services.implementations;

import edu.domain.entities.Product;
import edu.domain.exceptions.ProductNotFoundException;
import edu.domain.repositories.interfaces.ProductRepository;
import edu.domain.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DefaultProductService implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findProductById(Long id) {
        Product found = productRepository.findProductById(id);

        if (found == null) throw new ProductNotFoundException(id);

        return found;
    }

}
