package cft.shift.manasyan.barter.repositories.databases.interfaces;

import cft.shift.manasyan.barter.models.Product;

import java.util.Collection;

public interface ProductRepository {
    Product fetchProduct(String productId);

    Collection<Product> fetchUserProducts(String userId);

    Collection<Product> getAllProducts();

    Product updateProduct(String productId, Product product);

    void deleteProduct(String productId);

    Product createProduct(Product product);
}
