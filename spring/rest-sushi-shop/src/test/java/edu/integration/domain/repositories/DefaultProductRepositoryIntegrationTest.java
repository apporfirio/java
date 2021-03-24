package edu.integration.domain.repositories;


import edu.config.ApplicationContextConfig;
import edu.domain.entities.Product;

import edu.domain.repositories.interfaces.ProductRepository;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationContextConfig.class)
public class DefaultProductRepositoryIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void shouldFindProductById() {
        // provide a product to be found in database
        Product existingProduct = new Product(
                null,
                "Poke Veggie",
                "Grilled tofu, rice, green onions, cucumber",
                21.00f
        );
        productRepository.saveProduct(existingProduct);
        //

        Product found = productRepository.findProductById(existingProduct.getId());

        assertEquals(existingProduct, found);
    }

    @Test
    public void shouldFindProductsContainingTitle() {
        // provide products to be found in database
        List<Product> existingProducts = Arrays.asList(
                new Product(
                        null,
                        "Green Roll",
                        "Cucumber and mango wrapped in nori",
                        21.00f
                ),
                new Product(
                        null,
                        "Hot Green",
                        "Fried sushi with cucumber, mango and vegan mayo",
                        21.00f
                ),
                new Product(
                        null,
                        "Poke Green",
                        "Grilled shimeji, rice, green onions",
                        21.00f
                )
        );
        for (Product existingProduct : existingProducts) {
            productRepository.saveProduct(existingProduct);
        }
        //

        List<Product> foundProducts = productRepository.findProductsContainingTitle("Green");

        assertEquals(existingProducts.size(), foundProducts.size());
        for (Product foundProduct : foundProducts) {
            assertTrue(existingProducts.contains(foundProduct));
        }
    }

    @Test
    public void shouldNotSaveProductGivenNullTitle() {
        try {
            Product product = new Product(
                    null,
                    null,
                    "Null title product cannot be saved",
                    9999.999f
            );
            productRepository.saveProduct(product);
        }
        catch (DataIntegrityViolationException e) {
            assertTrue(e.getCause() instanceof PropertyValueException);
        }
        catch (Exception e) {
            throw new RuntimeException("shouldNotSaveProductGivenNullTitle failed");
        }
    }

    @Test
    public void shouldNotSaveProductGivenNullDescription() {
        try {
            Product product = new Product(
                    null,
                    "Null description product cannot be saved",
                    null,
                    9999.999f
            );
            productRepository.saveProduct(product);
        }
        catch (DataIntegrityViolationException e) {
            assertTrue(e.getCause() instanceof PropertyValueException);
        }
        catch (Exception e) {
            throw new RuntimeException("shouldNotSaveProductGivenNullDescription failed");
        }
    }

    @Test
    public void shouldNotSaveProductGivenNullPrice() {
        try {
            Product product = new Product(
                    null,
                    "Null price product",
                    "Null price product cannot be saved",
                    null
            );
            productRepository.saveProduct(product);
        }
        catch (DataIntegrityViolationException e) {
            assertTrue(e.getCause() instanceof PropertyValueException);
        }
        catch (Exception e) {
            throw new RuntimeException("shouldNotSaveProductGivenNullPrice failed");
        }
    }

    @Test
    public void shouldNotSaveProductGivenSameTitle() {
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
            productRepository.saveProduct(product);
            productRepository.saveProduct(productSameTitle);
        }
        catch (DataIntegrityViolationException e) {
            assertTrue(e.getCause() instanceof ConstraintViolationException);
        }
        catch (Exception e) {
            throw new RuntimeException("shouldNotSaveProductGivenSameTitle failed");
        }
    }

    @Test
    public void shouldCreateProduct() {
        Product product = new Product(
                null,
                "Hot Roll Salmon (4 units)",
                "Fried salmon sushi",
                15.50f
        );

        productRepository.saveProduct(product);

        assertTrue(product.getId() > 0);
    }

    @Test
    public void shouldUpdateProduct() {
        // provide a product to be updated in database
        Product toBeUpdated = new Product(
                null,
                "Double Hot Roll Salmon (8 units)",
                "Fried salmon sushi",
                30.00f
        );
        productRepository.saveProduct(toBeUpdated);
        //
        Product updatedProduct = new Product(
                toBeUpdated.getId(),
                toBeUpdated.getTitle(),
                toBeUpdated.getDescription() + ".",
                2 * toBeUpdated.getPrice()
        );

        productRepository.saveProduct(updatedProduct);

        Product found = productRepository.findProductById(updatedProduct.getId());
        assertEquals(updatedProduct, found);
    }

}
