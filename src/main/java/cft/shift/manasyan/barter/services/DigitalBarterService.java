package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.Offer;
import cft.shift.manasyan.barter.models.OfferResponse;
import cft.shift.manasyan.barter.models.Person;
import cft.shift.manasyan.barter.repositories.BarterOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DigitalBarterService {
    private BarterOfferRepository desiresRepository;
    private BarterOfferRepository suggestionsRepository;

    private Map<String, Person> persons;

    @Autowired
    public DigitalBarterService(BarterOfferRepository desiresRepository, BarterOfferRepository suggestionsRepository) {
        this.desiresRepository = desiresRepository;
        this.suggestionsRepository = suggestionsRepository;
        this.persons = new HashMap<>();
    }

    public void addDesire(Offer desire){
        desiresRepository.addOffer(desire);
    }

    public void addSuggest(Offer suggest){
         suggestionsRepository.addOffer(suggest);
    }

    public List<Offer> getDesires() {
        return desiresRepository.getOffers();
    }

    public List<Offer> getSuggests() {
        return suggestionsRepository.getOffers();
    }

    public List<Person> getPersons() {
        return new ArrayList<>(persons.values());
    }

    public Person getPerson(String userId){
        return persons.get(userId);
    }

    public Offer getDesire(String offerId){
        return desiresRepository.getOffer(offerId);
    }

    public Offer getSuggestion(String offerId){
        return suggestionsRepository.getOffer(offerId);
    }

    public void closeDesire(String offerId, String responseId){
        closeOffer(offerId, responseId, desiresRepository);
    }

    public void closeSuggest(String offerId, String responseId){
        closeOffer(offerId, responseId, suggestionsRepository);
    }

    private void closeOffer(String offerId, String responseId, BarterOfferRepository barterOfferRepository){
        Offer offer = barterOfferRepository.getOffer(offerId);
//        OfferResponse offerResponse =
    }
}
