package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class BarterOfferRepository implements OfferRepository {
    private List<Offer> offers;

    private BarterOfferRepository(){
        this.offers = new LinkedList<>();
    }

    private BarterOfferRepository(List<Offer> offers){
        this.offers = offers;
    }

    @Override
    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    @Override
    public void closeOffer(Offer offer) {
        if (!offers.remove(offer)){
            throw new NotFoundException();
        }
    }

    @Override
    public List<Offer> getOffers() {
        return null;
    }
}
