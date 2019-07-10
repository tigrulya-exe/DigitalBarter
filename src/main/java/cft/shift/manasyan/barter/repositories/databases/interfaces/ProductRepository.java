package cft.shift.manasyan.barter.repositories.databases.interfaces;

import cft.shift.manasyan.barter.models.Product;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Repository
public interface ProductRepository {
    Product getProduct(String productId);

    List<Product> getUserProducts(String userId);

    List<Product> getAllProducts();

    Product updateProduct(Product product);

    void deleteProduct(String productId);

    Product putProduct(Product product);

    void setNewProductsToUser(Map<String, Product> products);
}
