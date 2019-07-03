package cft.shift.manasyan.barter.models;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Backpack {
    private Map<String, Product> products;

    public Backpack(){
        this.products = new HashMap<>();
    }

    public Backpack(Map<String, Product> products){
        this.products = products;
    }

    public void addProduct(Product product){
        products.put(product.getId() , product);
    }

    public void deleteProduct(String productId){
        products.remove(productId);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products.values()) ;
    }

    public void putProduct(Product product){
        products.put(product.getId(), product);
    }

    public Product getProduct(String productId){
        Product product = products.get(productId);
        deleteProduct(productId);
        return product;
    }
}
