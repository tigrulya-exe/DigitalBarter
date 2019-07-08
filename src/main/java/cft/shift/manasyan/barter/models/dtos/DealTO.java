package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.deals.Deal;

public class DealTO {
    private String name;
    private String description;
    private String id;
    private Product product;

    public DealTO(){}

    public DealTO(Deal deal){
        this.name = deal.getDealHolder().getName();
        this.description = deal.getDescription();
        this.product = deal.getDealProduct();
        this.id = deal.getId();
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = this.product;
    }
}
