package cft.shift.manasyan.barter.models;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Person {
    private String name;
    @Autowired
    private Backpack backpack;
    private List<Product> offeredProducts;

    public Person(String name) {
        this.name = name;
        this.offeredProducts = offeredProducts;
    }

}
