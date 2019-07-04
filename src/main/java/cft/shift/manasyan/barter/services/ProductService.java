package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.User;
import cft.shift.manasyan.barter.models.Product;

import java.net.URL;

public class ProductService {
    public Product createProduct(User owner, Product.ProductType type, String name, String  picURL)
    {
        Product prod = new Product(type, name, picURL);
        owner.getBackpack().addProduct(prod);
        return prod;
    }
}
