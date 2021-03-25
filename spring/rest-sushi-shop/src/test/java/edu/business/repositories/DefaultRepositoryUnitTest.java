package edu.business.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;


@RunWith(MockitoJUnitRunner.class)
public class DefaultRepositoryUnitTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private final ProductRepository productRepository = new DefaultProductRepository();

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotFindProductsContainingTitleGivenNullTitle() {
        productRepository.findProductsContainingTitle(null);
    }

}
