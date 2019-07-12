package cft.shift.manasyan.barter.models.user;

import cft.shift.manasyan.barter.models.deals.Deal;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDeals {
    private List<Deal> desires = new ArrayList<>();
    private List<Deal> offers = new ArrayList<>();

    public List<Deal> getDesires() {
        return desires;
    }

    public List<Deal> getOffers() {
        return offers;
    }

    public void addDesire(Deal desire){
        desires.add(desire);
    }

    public void addOffer(Deal offer){
        offers.add(offer);
    }

    public void deleteDesire(Deal desire){
        desires.remove(desire);
    }

    public void deleteOffer(Deal offer){
        offers.remove(offer);
    }
}