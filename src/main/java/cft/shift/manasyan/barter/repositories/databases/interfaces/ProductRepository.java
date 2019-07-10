package cft.shift.manasyan.barter.repositories.databases.interfaces;

import cft.shift.manasyan.barter.models.Product;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository {
    Product getProduct(String productId);

    List<Product> getUserProducts(String userId);

    List<Product> getAllProducts();

    Product updateProduct(Product product);

    void deleteProduct(String productId);

    Product putProduct(Product product);
}
