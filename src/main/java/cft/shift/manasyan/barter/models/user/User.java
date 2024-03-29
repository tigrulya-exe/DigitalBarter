package cft.shift.manasyan.barter.models.user;

import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.responses.DesireResponse;

import java.util.List;
import java.util.UUID;

public class User {
    private String name;
    private Backpack backpack;

    private String uid = UUID.randomUUID().toString();

    private UserDeals userDeals;

    private UserResponses userResponses;

    public User(String name) {
        this.name = name;
        this.backpack = new Backpack();
        this.userDeals = new UserDeals();
        this.userResponses = new UserResponses();
    }

    public String getName() {
        return name;
    }

    public UserDeals getUserDeals() {
        return userDeals;
    }

    public UserResponses getUserResponses() {
        return userResponses;
    }

    public List<DealResponse> getOfferResponses() {
        return userResponses.getOfferResponses();
    }

    public List<DesireResponse> getDesireResponses(){
        return userResponses.getDesireResponses();
    }

    public String getUid(){
        return uid;
    }

    public Backpack getBackpack(){
        return backpack;
    }

}