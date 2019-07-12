package cft.shift.manasyan.barter.models.user;

import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.responses.DesireResponse;

import java.util.List;
import java.util.UUID;

/*OK*/
public class User {
    private String name;
    private Backpack backpack;

    private String id = UUID.randomUUID().toString();

    private UserDeals userDeals;

    private UserResponses userResponses;

    public User(String name) {
        this.name = name;
        this.backpack = new Backpack(this.id);
        this.userDeals = new UserDeals(this.id);
        this.userResponses = new UserResponses();
    }
    public User(String name, String userId) {
        this.name = name;
        this.backpack = new Backpack(userId);
        this.userDeals = new UserDeals(userId);
        this.userResponses = new UserResponses();
        this.id = userId;
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

    public String getId(){
        return id;
    }

    public Backpack getBackpack(){
        return backpack;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }
}