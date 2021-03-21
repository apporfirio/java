package edu.api.unit;

import edu.api.ProductController;
import edu.domain.entities.Product;
import edu.domain.services.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ProductControllerUnitTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Test
    public void shouldNotFindProductByIDGivenNegativeID() {
        when(productService.findProductByID(-1L)).thenReturn(null);

        ResponseEntity<Product> response = productController.findProductByID(-1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

}
