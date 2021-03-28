package edu.business.services;

import edu.business.entities.Product;
import edu.crosscutting.exceptions.ProductNotFoundException;
import edu.business.repositories.ProductRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultProductServiceUnitTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private final ProductService productService = new DefaultProductService();

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFindProductByIdGivenNullId() {
        productService.findProductById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFindProductByIdGivenNegativeId() {
        productService.findProductById(-10l);
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldNotFindProductByIdGivenNonExistingId() {
        Long nonExistingId = 1000000000l;
        when(productRepository.findProductById(nonExistingId)).thenReturn(null);

        productService.findProductById(nonExistingId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFindProductsContainingTitleGivenNullTitle() {
        productService.findProductsContainingTitle(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFindProductsContainingTitleGivenEmptyTitle() {
        productService.findProductsContainingTitle("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFindProductsContainingTitleGivenBlankTitle() {
        productService.findProductsContainingTitle("   \n    \n");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateProductGivenNullProduct() {
        productService.createProduct(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotCreateProductGivenNonNullId() {
        Product product = new Product(
                59l,
                "Product title",
                "Product description",
                0.50f
        );
        productService.createProduct(product);
    }
}
