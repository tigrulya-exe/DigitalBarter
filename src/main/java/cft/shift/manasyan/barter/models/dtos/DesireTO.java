package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.deals.Desire;

public class DesireTO {
    private String description;
    private Product product;

    public DesireTO(){}

    public DesireTO(String description, Product product) {
        this.description = description;
        this.product = product;
    }
    public DesireTO(Desire desire)
    {
        description = desire.getDescription();
        product = desire.getDealProduct();
    }
    public String getDescription() {
        return description;
    }

    public Product getProduct() {
        return product;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
