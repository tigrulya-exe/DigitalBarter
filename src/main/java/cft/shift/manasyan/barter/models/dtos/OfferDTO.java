package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.Product;

public class OfferDTO {
    private String description;
    private String productId;

    public OfferDTO(String description, String productId) {
        this.description = description;
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public String getProductId() {
        return productId;
    }
}
