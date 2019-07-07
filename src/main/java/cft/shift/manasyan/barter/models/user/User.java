package cft.shift.manasyan.barter.models.user;

import java.util.UUID;

public class User {
    private String name;
    private Backpack backpack;

    private String uid = UUID.randomUUID().toString();

    private UserDeals userDeals;

    public User(String name) {
        this.name = name;
        this.backpack = new Backpack();
        this.userDeals = new UserDeals();
    }
    public User(String name, String userId) {
        this.name = name;
        this.backpack = new Backpack();
        this.userDeals = new UserDeals();
        this.uid = userId;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}