package edu.business.repositories;


import edu.config.ApplicationContextConfig;
import edu.business.entities.Product;

import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationContextConfig.class)
public class DefaultProductRepositoryIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldNoteCreateProductGivenNullTitle() {
        try {
            Product product = new Product(
                    null,
                    null,
                    "Null title product cannot be saved",
                    9999.999f
            );
            productRepository.createProduct(product);
        }
        catch (DataIntegrityViolationException e) {
            assertTrue(e.getCause() instanceof PropertyValueException);
        }
    }

    @Test
    public void shouldNoteCreateProductGivenNullDescription() {
        try {
            Product product = new Product(
                    null,
                    "Null description product cannot be saved",
                    null,
                    9999.999f
            );
            productRepository.createProduct(product);
        }
        catch (DataIntegrityViolationException e) {
            assertTrue(e.getCause() instanceof PropertyValueException);
        }
    }

    @Test
    public void shouldNoteCreateProductGivenNullPrice() {
        try {
            Product product = new Product(
                    null,
                    "Null price product",
                    "Null price product cannot be saved",
                    null
            );
            productRepository.createProduct(product);
        }
        catch (DataIntegrityViolationException e) {
            assertTrue(e.getCause() instanceof PropertyValueException);
        }
    }

    @Test
    public void shouldNoteCreateProductGivenNonUniqueTitle() {
        try {
            Product product = new Product(
                    null,
                    "Product title",
                    "Product description",
                    17.00f
            );
            Product productSameTitle = new Product(
                    null,
                    "Product title",
                    "Product description new",
                    18.00f
            );
            productRepository.createProduct(product);
            productRepository.createProduct(productSameTitle);
        }
        catch (DataIntegrityViolationException e) {
            assertTrue(e.getCause() instanceof ConstraintViolationException);
        }
    }
}
