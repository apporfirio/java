package edu.domain.repositories;

import edu.domain.entities.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public Product findProductByID(Long id) {
        return em.find(Product.class, id);
    }

}
