package cft.shift.manasyan.barter.models;

import java.util.UUID;

public class User {
    private String name;
    private Backpack backpack;

    private String uid = UUID.randomUUID().toString();

    private UserDeals userDeals;

    public User(String name) {
        this.name = name;
        this.backpack = new Backpack();
    }

    public String getName() {
        return name;
    }

    public UserDeals getUserDeals() {
        return userDeals;
    }

    public String getUid(){
        return uid;
    }

    public Backpack getBackpack(){
        return backpack;
    }
}
