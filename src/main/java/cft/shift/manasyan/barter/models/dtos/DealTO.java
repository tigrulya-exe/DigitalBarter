package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.Product;

public class DealTO {
    private String name;
    private Product product;

    public DealTO(String name, Product product) {
        this.name = name;
        this.product = product;
    }
}
