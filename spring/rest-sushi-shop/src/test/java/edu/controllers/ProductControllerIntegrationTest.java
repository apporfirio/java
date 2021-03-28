package edu.controllers;

import edu.business.entities.Product;
import edu.config.ApplicationContextConfig;
import edu.crosscutting.utils.Json;
import edu.business.responseBodies.ProductResponseBody;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationContextConfig.class)
public class ProductControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldNotFindProductByIdGivenNegativeId() throws Exception {
        String responseBodyString = mockMvc.perform(get("/product/{id}", -9))
                                      .andDo(print())
                                      .andExpect(status().isBadRequest())
                                      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                                      .andReturn()
                                      .getResponse()
                                      .getContentAsString();

        ProductResponseBody responseBody = Json.fromString(responseBodyString, ProductResponseBody.class);
        assertNotNull(responseBody.getErrorMessage());
    }

    @Test
    public void shouldNotFindProductByIdGivenNonExistingId() throws Exception {
        String responseBodyString = mockMvc.perform(get("/product/{id}", 1000000000))
                                      .andDo(print())
                                      .andExpect(status().isNotFound())
                                      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                                      .andReturn()
                                      .getResponse()
                                      .getContentAsString();

        ProductResponseBody responseBody = Json.fromString(responseBodyString, ProductResponseBody.class);
        assertNotNull(responseBody.getErrorMessage());
    }

    @Test
    public void shouldFindProductById() throws Exception {
        Product providedProduct = provideProductToBeFound();

        String responseBodyString = mockMvc.perform(get("/product/{id}", providedProduct.getId()))
                                      .andDo(print())
                                      .andExpect(status().isOk())
                                      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                                      .andReturn()
                                      .getResponse()
                                      .getContentAsString();

        ProductResponseBody responseBody = Json.fromString(responseBodyString, ProductResponseBody.class);
        assertEquals(providedProduct, responseBody.getProduct());
    }
    private Product provideProductToBeFound() throws Exception {
        Product product = new Product(
                null,
                "Sunomono Salad (16 units)",
                "Thin slices of sweet cucumber",
                5.00f
        );

        String responseBodyString = mockMvc.perform(post("/product/")
                                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                                .content(Json.toString(product)))
                                      .andReturn()
                                      .getResponse()
                                      .getContentAsString();

        ProductResponseBody responseBody = Json.fromString(responseBodyString, ProductResponseBody.class);
        product.setId(responseBody.getGeneratedId());
        return product;
    }

    @Test
    public void shouldNotCreateProductGivenNonNullId() throws Exception {
        Product toBeCreated = new Product(
                13L,
                "Sunomono Salad",
                "Thin slices of sweet cucumber",
                2.50f
        );

        mockMvc.perform(post("/product/")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(Json.toString(toBeCreated)))
               .andDo(print())
               .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldCreateProduct() throws Exception {
        Product toBeCreated = new Product(
                null,
                "Sunomono Salad (8 units)",
                "Thin slices of sweet cucumber",
                2.50f
        );

        String responseBodyString = mockMvc.perform(post("/product/")
                                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                                .content(Json.toString(toBeCreated)))
                                      .andDo(print())
                                      .andExpect(status().isOk())
                                      .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                                      .andReturn()
                                      .getResponse()
                                      .getContentAsString();

        ProductResponseBody responseBody = Json.fromString(responseBodyString, ProductResponseBody.class);
        assertTrue(responseBody.getGeneratedId() > 0);
    }

}
