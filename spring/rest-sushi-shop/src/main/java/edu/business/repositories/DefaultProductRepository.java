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

        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.title LIKE :titleParam",
                Product.class
        );
        query.setParameter("titleParam", "%" + title + "%");

        return query.getResultList();
    }

    public Product saveProduct(Product product) {
        if (product.getId() == null) {
            return createProduct(product);
        }

        return updateProduct(product);
    }
    private Product createProduct(Product product) {
        em.persist(product);
        return product;
    }
    private Product updateProduct(Product product) {
        Product managedProduct = findProductById(product.getId());

        managedProduct.setTitle(product.getTitle());
        managedProduct.setDescription(product.getDescription());
        managedProduct.setPrice(product.getPrice());

        return managedProduct;
    }

}
