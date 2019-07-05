package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.Offer;
import cft.shift.manasyan.barter.models.Product;

public class OfferDTO {
    private String description;
    private String productId;

    public OfferDTO() {
    }

    public OfferDTO(String description, String productId) {
        this.description = description;
        this.productId = productId;
    }

    public OfferDTO(Offer offer)
    {
        description = offer.getDescription();
        productId = offer.getDealProduct().getId();
    }
    public String getDescription() {
        return description;
    }

    public String getProductId() {
        return productId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
