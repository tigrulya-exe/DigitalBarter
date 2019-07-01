package cft.shift.manasyan.barter.models;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Backpack {
    private List<Product> products;

    public Backpack(){
        this.products = new LinkedList<>();
    }

    public Backpack(List<Product> products){
        this.products = products;
    }

    public boolean deleteProduct(Product product){
        return products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}
