package cft.shift.manasyan.barter.models;

import java.util.LinkedList;
import java.util.List;

public class Person {
    private String name;
    private Backpack backpack;

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

    }
}
