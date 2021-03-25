package edu.business.services;

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
        productService.findProductById(-10L);
    }

    @Test(expected = ProductNotFoundException.class)
    public void shouldNotFindProductByIdGivenNonExistingId() {
        Long nonExistingId = 1000000000L;
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
    public void shouldNotSaveProductGivenNullProduct() {
        productService.saveProduct(null);
    }


}
