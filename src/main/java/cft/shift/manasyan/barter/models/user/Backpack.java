package cft.shift.manasyan.barter.models.user;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.repositories.databases.disk.DatabaseProductRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

//@Component
public class Backpack {

    @Autowired
    @Qualifier("sqlProducts")
    private DatabaseProductRepository databaseProductRepository;

    private Map<String, Product> products = new HashMap<>();

    public Backpack(String userId){
        for(Product item: databaseProductRepository.getUserProducts(userId)) {
            products.put(item.getId(), item);
        }
    }

    /*public Backpack(@NonNull Map<String, Product> products){
        this.products = products;
    }*/

    /*public Backpack(@NonNull List<Product> productsIn){
        this.products = new HashMap<>();

        for(Product product: productsIn){
            products.put(product.getId(), product);
            databaseProductRepository.putProduct(product);
        }
    }*/

    public void putProduct(@NonNull Product product, String userId){
        product.setUserID(userId);
        products.put(product.getId() , product);
        databaseProductRepository.putProduct(product);
    }

    public void deleteProduct(String productId){
        if (products.remove(productId) == null)
            throw new NotFoundException("Wrong productId");
        databaseProductRepository.deleteProduct(productId);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products.values()) ;
    }


    public Product getAndDeleteProduct(String productId){
        Product product = products.get(productId);
        product.setUserID("0");
        deleteProduct(productId);
        databaseProductRepository.updateProduct(product);
        return product;
    }

    public Product getProduct(String productId){
        Product product =  products.get(productId);
        if (product == null)
            throw new NotFoundException("Wrong productId");
        return product;
    }
}
