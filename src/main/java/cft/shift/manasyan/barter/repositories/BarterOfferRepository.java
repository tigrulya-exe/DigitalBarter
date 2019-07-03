package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BarterOfferRepository implements OfferRepository {

    private Map<String, Offer> offers;

    private BarterOfferRepository(){
        this.offers = new HashMap<>();
    }

    private BarterOfferRepository(Map<String, Offer> offers){
        this.offers = offers;
    }

    @Override
    public void addOffer(Offer offer) {
        offers.put(offer.getId(), offer);
    }

    @Override
    public void closeOffer(String offerId) {

        if (offers.remove(offerId) == null){
            throw new NotFoundException();
        }
    }

    @Override
    public List<Offer> getOffers() {
        return new ArrayList<>(offers.values());
    }

    @Override
    public Offer getOffer(String offerId) {
        return offers.get(offerId);
    }


}
