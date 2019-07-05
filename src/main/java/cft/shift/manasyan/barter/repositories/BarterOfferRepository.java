package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.Deal;
import cft.shift.manasyan.barter.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class BarterOfferRepository{

    private Map<String, Offer> Offers;

    public BarterOfferRepository(){
        this.Offers = new HashMap<>();
    }

    private BarterOfferRepository(Map<String, Offer> Offers){
        this.Offers = Offers;
    }

    public void addOffer(Offer offer) {
        Offers.put(offer.getId(), offer);
    }

    public void closeOffer(String offerId) {

        if (Offers.remove(offerId) == null){
            throw new NotFoundException();
        }
    }

    public List<Offer> getOffers() {
        return new ArrayList<>(Offers.values());
    }

    public Offer getOffer(String offerId) {
        return Offers.get(offerId);
    }


}
