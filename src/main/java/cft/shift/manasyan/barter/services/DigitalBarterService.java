package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.OfferGet;
import cft.shift.manasyan.barter.models.OfferGive;
import cft.shift.manasyan.barter.repositories.BarterOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigitalBarterService {
    private BarterOfferRepository<OfferGet> desiresRepository;
    private BarterOfferRepository<OfferGive> suggestsRepository;

    @Autowired
    public DigitalBarterService(BarterOfferRepository<OfferGet> desiresRepository, BarterOfferRepository<OfferGive> suggestsRepository) {
        this.desiresRepository = desiresRepository;
        this.suggestsRepository = suggestsRepository;
    }

    public void addDesire(OfferGet desire){
        desiresRepository.addOffer(desire);
    }

    public void addSuggest(OfferGive suggest){
         suggestsRepository.addOffer(suggest);
    }

    public List<OfferGet> getDesires() {
        return desiresRepository.getOffers();
    }

    public List<OfferGive> getSuggests() {
        return suggestsRepository.getOffers();
    }
}
