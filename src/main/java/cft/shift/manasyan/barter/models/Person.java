package cft.shift.manasyan.barter.models;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Person {
    private String name;
    private Backpack backpack;

    private String uid = UUID.randomUUID().toString();

    //TODO mb it ll be better to wrap it into class
    private List<Product> offeredProducts = new LinkedList<>();

    public Person(String name) {
        this.name = name;
        this.backpack = new Backpack();
    }

    public String getName() {
        return name;
    }

    public List<Product> getOfferedProducts() {
        return offeredProducts;
    }

    public void addOfferedProduct(Product product){
        offeredProducts.add(product);
    }

    public void putInBackpack(Product product){
        backpack.addProduct(product);
    }

    public String getUid(){
        return uid;
    }

    public Backpack getBackpack(){
        return backpack;
    }
}
