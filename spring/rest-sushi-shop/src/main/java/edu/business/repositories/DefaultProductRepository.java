package edu.business.repositories;

import static edu.crosscutting.utils.Assertions.assertNotNull;

import edu.business.entities.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Primary
@Transactional
public class DefaultProductRepository implements ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public Product findProductById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findProductsContainingTitle(String title) {
        assertNotNull(title, "title");
        // para não gerar equivalência com quando title = "null"

        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.title LIKE :titleParam",
                Product.class
        );
        query.setParameter("titleParam", "%" + title + "%");

        return query.getResultList();
    }

    public void createProduct(Product product) {
        em.persist(product);
        // seta o id gerado em product
    }
}
