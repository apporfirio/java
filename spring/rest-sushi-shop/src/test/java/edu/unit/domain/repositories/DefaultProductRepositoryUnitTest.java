package edu.unit.domain.repositories;

import edu.domain.repositories.implementations.DefaultProductRepository;
import edu.domain.repositories.interfaces.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;

@RunWith(MockitoJUnitRunner.class)
public class DefaultProductRepositoryUnitTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private final ProductRepository productRepository = new DefaultProductRepository();

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFindProductByIdGivenNullId() {
        productRepository.findProductById(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFindProductByIdGivenNegativeId() {
        productRepository.findProductById(-10L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFindProductsContainingTitleGivenNullTitle() {
        productRepository.findProductsContainingTitle(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFindProductsContainingTitleGivenEmptyTitle() {
        productRepository.findProductsContainingTitle("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFindProductsContainingTitleGivenBlankTitle() {
        productRepository.findProductsContainingTitle("   \n    \n");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotSaveProductGivenNullProduct() {
        productRepository.saveProduct(null);
    }

}
