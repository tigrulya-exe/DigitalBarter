package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;

@Repository
public class BarterOfferRepository<T extends Offer> implements OfferRepository<T> {
    private List<T> offers;

    private BarterOfferRepository(){
        this.offers = new LinkedList<>();
    }

    private BarterOfferRepository(List<T> offers){
        this.offers = offers;
    }

    @Override
    public void addOffer(T offer) {
        offers.add(offer);
    }

    @Override
    public void closeOffer(T offer) {
        if (!offers.remove(offer)){
            throw new NotFoundException();
        }
    }

    @Override
    public List<T> getOffers() {
        return offers;
    }
}
