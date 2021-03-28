package edu.business.services;

import edu.config.ApplicationContextConfig;
import edu.business.entities.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationContextConfig.class)
public class DefaultProductServiceIntegrationTest {

    @Autowired
    private ProductService productService;

    @Test
    public void shouldFindProductById() {
        // provide a product to be found in database
        Product existingProduct = new Product(
                null,
                "Poke Veggie",
                "Grilled tofu, rice, green onions, cucumber",
                21.00f
        );
        Long generatedId = productService.createProduct(existingProduct);
        //

        Product found = productService.findProductById(generatedId);

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
            productService.createProduct(existingProduct);
        }
        //

        List<Product> foundProducts = productService.findProductsContainingTitle("Green");

        assertEquals(existingProducts.size(), foundProducts.size());
        for (Product foundProduct : foundProducts) {
            assertTrue(existingProducts.contains(foundProduct));
        }
    }

    @Test
    public void shouldCreateProduct() {
        Product toBeCreated = new Product(
                null,
                "Hot Roll Salmon (4 units)",
                "Fried salmon sushi",
                15.50f
        );

        Long generatedId = productService.createProduct(toBeCreated);

        Product found = productService.findProductById(generatedId);
        assertEquals(toBeCreated, found);
    }

//    @Test
//    public void shouldUpdateProduct() {
//        // provide a product to be updated in database
//        Product toBeUpdated = new Product(
//                null,
//                "Double Hot Roll Salmon (8 units)",
//                "Fried salmon sushi",
//                30.00f
//        );
//        productService.createProduct(toBeUpdated);
//        //
//        Product updatedProduct = new Product(
//                toBeUpdated.getId(),
//                toBeUpdated.getTitle(),
//                toBeUpdated.getDescription() + ".",
//                2 * toBeUpdated.getPrice()
//        );
//
//        productService.createProduct(updatedProduct);
//
//        Product found = productService.findProductById(updatedProduct.getId());
//        assertEquals(updatedProduct, found);
//    }
}
