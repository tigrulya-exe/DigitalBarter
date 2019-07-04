package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.Person;
import cft.shift.manasyan.barter.models.Product;

import java.net.URL;

public class ProductService {
    public Product createProduct(Person owner, Product.ProductType type, String name, String condition, URL picURL)
    {
        Product prod = new Product(type, name, condition, picURL);
        owner.getBackpack().addProduct(prod);
        return prod;
    }
}
