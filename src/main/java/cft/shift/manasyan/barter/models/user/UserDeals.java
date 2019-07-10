package cft.shift.manasyan.barter.models.user;

import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.models.deals.Offer;
import cft.shift.manasyan.barter.repositories.databases.disk.DatabaseDesireRepository;
import cft.shift.manasyan.barter.repositories.databases.disk.DatabaseOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*OK*/
public class UserDeals {
    @Autowired
    @Qualifier("sql")
    private DatabaseDesireRepository databaseDesireRepository;

    @Autowired
    @Qualifier("sql")
    private DatabaseOfferRepository databaseOfferRepository;

    private List<Desire> desires = new ArrayList<>();
    private List<Offer> offers = new ArrayList<>();
    public UserDeals(String userId)
    {
        desires = databaseDesireRepository.getUserDeals(userId);
        offers = databaseOfferRepository.getUserDeals(userId);
    }
    public List<Desire> getDesires() {
        return desires;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void addDesire(Desire desire){
        desires.add(desire);
        databaseDesireRepository.addDeal(desire);
    }

    public void addOffer(Offer offer){
        offers.add(offer);
        databaseOfferRepository.addDeal(offer);
    }

    public void deleteDesire(Desire desire){
        desires.remove(desire);
        databaseDesireRepository.removeDeal(desire.getId());
    }

    public void deleteOffer(Offer offer){
        offers.remove(offer);
        databaseOfferRepository.removeDeal(offer.getId());
    }
}