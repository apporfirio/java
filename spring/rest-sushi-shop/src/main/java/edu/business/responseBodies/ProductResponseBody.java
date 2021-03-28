package edu.business.responseBodies;

import edu.business.entities.Product;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductResponseBody {

    private Long generatedId;
    private Product product;
    private String errorMessage;

}
