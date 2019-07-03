package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository<T extends Offer> {
    void addOffer(T offer);

    void closeOffer(T offer);

    List<T> getOffers();
}
