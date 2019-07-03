package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.Offer;
import cft.shift.manasyan.barter.models.Person;
import cft.shift.manasyan.barter.repositories.BarterOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DigitalBarterService {
    private BarterOfferRepository<Offer> desiresRepository;
    private BarterOfferRepository<Offer> suggestsRepository;

    private List<Person> persons;

    @Autowired
    public DigitalBarterService(BarterOfferRepository<Offer> desiresRepository, BarterOfferRepository<Offer> suggestsRepository) {
        this.desiresRepository = desiresRepository;
        this.suggestsRepository = suggestsRepository;
        this.persons = new ArrayList<>();
    }

    public void addDesire(Offer desire){
        desiresRepository.addOffer(desire);
    }

    public void addSuggest(Offer suggest){
         suggestsRepository.addOffer(suggest);
    }

    public List<Offer> getDesires() {
        return desiresRepository.getOffers();
    }

    public List<Offer> getSuggests() {
        return suggestsRepository.getOffers();
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Person getPerson(String name){
        return null;
    }
}
