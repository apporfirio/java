package edu.domain.services.unit;

import edu.domain.entities.Product;
import edu.domain.repositories.ProductRepository;
import edu.domain.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceUnitTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void shouldNotFindProductByIDGivenNegativeID() {
        when(productRepository.findProductByID(-1L)).thenReturn(null);

        Product product = productService.findProductByID(-1L);

        assertNull(product);
    }

    @Test
    public void shouldFindProductByIDGivenExistingID() {
        Long existingID = 2000L;
        Product toBeFound = mock(Product.class);
        toBeFound.setId(existingID);
        when(productRepository.findProductByID(existingID)).thenReturn(toBeFound);

        Product product = productService.findProductByID(existingID);

        assertEquals(toBeFound.getId(), product.getId());
    }

}
