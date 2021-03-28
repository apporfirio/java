package edu.controllers;

import edu.business.entities.Product;
import edu.business.services.ProductService;
import edu.crosscutting.exceptions.ProductNotFoundException;
import edu.business.responseBodies.ProductResponseBody;
import edu.business.responseBodies.ProductResponseBody.ProductResponseBodyBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController extends JsonResponseController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseBody> findProductById(@PathVariable Long id) {
        ProductResponseBodyBuilder responseBody = ProductResponseBody.builder();

        try {
            Product found = productService.findProductById(id);
            return ok(responseBody
                        .product(found)
                        .build());
        }
        catch (IllegalArgumentException e) {
            return badRequest(responseBody
                                .errorMessage(e.getMessage())
                                .build());
        }
        catch (ProductNotFoundException e) {
            return notFound(responseBody
                                .errorMessage(e.getMessage())
                                .build());
        }
    }

    @PostMapping("/")
    public ResponseEntity<ProductResponseBody> createProduct(@RequestBody Product product) {
        ProductResponseBodyBuilder responseBody = ProductResponseBody.builder();

        try {
            Long generatedId = productService.createProduct(product);
            return ok(responseBody
                    .generatedId(generatedId)
                    .build());
        }
        catch (IllegalArgumentException e) {
            return badRequest(responseBody
                    .errorMessage(e.getMessage())
                    .build());
        }
    }
}
