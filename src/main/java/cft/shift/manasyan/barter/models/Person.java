package cft.shift.manasyan.barter.models;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Person {
    private String name;
    private Backpack backpack;

    private String uid = UUID.randomUUID().toString();

    private UserOffers userOffers;

    public Person(String name) {
        this.name = name;
        this.backpack = new Backpack();
    }

    public String getName() {
        return name;
    }

    public UserOffers getUserOffers() {
        return userOffers;
    }

    public String getUid(){
        return uid;
    }

    public Backpack getBackpack(){
        return backpack;
    }
}
