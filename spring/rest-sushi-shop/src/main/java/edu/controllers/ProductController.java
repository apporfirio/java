package edu.controllers;

import edu.business.entities.Product;
import edu.crosscutting.exceptions.ProductNotFoundException;
import edu.business.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController extends AbstractController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findProductByID(@PathVariable Long id) {
        try {
            Product product = productService.findProductById(id);
            return ok(product);
        }
        catch (IllegalArgumentException e) {
            return badRequest(e);
        }
        catch (ProductNotFoundException e) {
            return notFound();
        }
    }
}
