package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository {
    void addOffer(Offer offer);

    void closeOffer(Offer offer);

    List<Offer> getOffers();
}
