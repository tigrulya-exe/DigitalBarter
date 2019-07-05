package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.Deal;
import cft.shift.manasyan.barter.models.Product;

import javax.management.Descriptor;

public class DealTO {
    private String name;
    private String description;
    private Product product;
    private String id;

    public DealTO(){};

    public DealTO(Deal deal){
        this.name = deal.getDealHolder().getName();
        this.description = deal.getDescription();
        this.product = deal.getDealProduct();
    }

    public DealTO(String name, String description ,Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
