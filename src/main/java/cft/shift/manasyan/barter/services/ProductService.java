package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.models.Product;

public class ProductService {
    public Product createProduct(User owner, Product.ProductType type, String name, String  picURL)
    {
        Product prod = new Product(type, name, picURL);
        owner.getBackpack().putProduct(prod);
        return prod;
    }
}
