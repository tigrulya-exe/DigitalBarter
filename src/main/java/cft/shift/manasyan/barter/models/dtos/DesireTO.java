package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.deals.Desire;

public class DesireTO {
    private String description;
    private ProductTO product;

    public DesireTO(){}

    public DesireTO(String description, ProductTO product) {
        this.description = description;
        this.product = product;
    }
    public DesireTO(Desire desire)
    {
        description = desire.getDescription();
        product = new ProductTO(desire.getDealProduct());
    }
    public String getDescription() {
        return description;
    }

    public ProductTO getProduct() {
        return product;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProduct(ProductTO product) {
        this.product = product;
    }
}
