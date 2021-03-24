package edu.domain.repositories.implementations;

import edu.domain.entities.Product;
import edu.domain.repositories.interfaces.ProductRepository;
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
        if (id == null || id < 0) {
            throw new IllegalArgumentException("cannot findProductById given null id or negative id");
        }

        return em.find(Product.class, id);
    }

    public List<Product> findProductsContainingTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("cannot findProductByTitle given null title or blank title");
        }

        TypedQuery<Product> query = em.createQuery(
                "SELECT p FROM Product p WHERE p.title LIKE :titleParam",
                Product.class
        );
        query.setParameter("titleParam", "%" + title + "%");

        return query.getResultList();
    }

    public Product saveProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("cannot save null product");
        }

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
