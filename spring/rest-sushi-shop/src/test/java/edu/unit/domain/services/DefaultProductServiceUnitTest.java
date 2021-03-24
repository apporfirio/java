package edu.unit.domain.services;

import edu.domain.entities.Product;
import edu.domain.exceptions.ProductNotFoundException;
import edu.domain.repositories.interfaces.ProductRepository;
import edu.domain.services.implementations.DefaultProductService;
import edu.domain.services.interfaces.ProductService;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultProductServiceUnitTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private final ProductService productService = new DefaultProductService();

    @Test
    public void shouldNotFindProductByIdGivenNullId() {
        Long sampleID = null;
        ProductNotFoundException expectedException = new ProductNotFoundException(sampleID);

        when(productRepository.findProductById(sampleID)).thenReturn(null);

        try {
            productService.findProductById(sampleID);
        }
        catch (ProductNotFoundException e) {
            assertEquals(expectedException.getMessage(), e.getMessage());
        }
        catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void shouldFindProductById() {
        Long sampleID = 20L;
        Product toBeFound = mock(Product.class);
        toBeFound.setId(sampleID);
        when(productRepository.findProductById(sampleID)).thenReturn(toBeFound);

        Product product = productService.findProductById(sampleID);

        assertEquals(toBeFound.getId(), product.getId());
    }


}
