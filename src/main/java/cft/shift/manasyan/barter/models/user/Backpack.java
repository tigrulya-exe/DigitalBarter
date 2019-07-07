package cft.shift.manasyan.barter.models.user;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.Product;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Backpack {
    private Map<String, Product> products;

    public Backpack(){
        this.products = new HashMap<>();
    }

    public Backpack(@NonNull Map<String, Product> products){
        this.products = products;
    }

    public void putProduct(@NonNull Product product){
        products.put(product.getId() , product);
    }

    public void deleteProduct(String productId){
        if (products.remove(productId) == null)
            throw new NotFoundException("Wrong productId");
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products.values()) ;
    }


    public Product getAndDeleteProduct(String productId){
        Product product = products.get(productId);
        deleteProduct(productId);
        return product;
    }

    public Product getProduct(String productId){
        Product product =  products.get(productId);
        if (product == null)
            throw new NotFoundException("Wrong productId");
        return product;
    }
}
