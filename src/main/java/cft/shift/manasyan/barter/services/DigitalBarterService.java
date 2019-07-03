package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.*;
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

    public void acceptDesire(String offerId, String responseId){
        acceptOffer(offerId, responseId, desiresRepository);
    }

    public void acceptSuggestion(String offerId, String responseId){
        acceptOffer(offerId, responseId, suggestionsRepository);
    }

    public  Offer createSuggestion(String userId, String productId){
        Offer suggestion = createOffer(userId, productId);
        //TODO сделать покрасивше
        suggestion.getOfferHolder().getUserOffers().addSuggestion(suggestion);
        suggestionsRepository.addOffer(suggestion);
        return suggestion;
    }

    public Offer createDesire(String userId, String productId) {
        Offer desire = createOffer(userId, productId);
        //TODO сделать покрасивше
        desire.getOfferHolder().getUserOffers().addDesire(desire);
        desiresRepository.addOffer(desire);
        return desire;
    }

    public Person createUser(String userName){
        Person user = new Person(userName);
        addUser(user);
        return user;
    }

    public void addUser(Person person){
        persons.put(person.getUid(),person);
    }

    public void handleSuggestResponseEvent(String offerId, String userId, String productId){
        Person owner = persons.get(userId) ;
        Offer suggestion = suggestionsRepository.getOffer(offerId);
        Product product = owner.getBackpack().getProduct(productId);
        suggestion.registerOfferResponse(owner, product);
    }

    //TODO check!!
    public void handleDesireResponseEvent(String offerId, String userId){
        Person owner = persons.get(userId);
        Offer desire = desiresRepository.getOffer(offerId);
        Product product = desire.getOfferProduct();
        desire.registerOfferResponse(owner , product);
    }

    public void putProductInBackpack(String userId, Product product){
        Backpack backpack =  persons.get(userId).getBackpack();
        backpack.putProduct(product);
    }

    private Offer createOffer(String userId, String productId){
        Person person = persons.get(userId);
        Product product = person.getBackpack().getProduct(productId);
        return new Offer(product, person);
    }

    private void acceptOffer(String offerId, String responseId, BarterOfferRepository barterOfferRepository){
        Offer offer = barterOfferRepository.getOffer(offerId);
        OfferResponse offerResponse = offer.getOfferResponse(responseId);
        barterOfferRepository.closeOffer(offerId);

        Person offerHolder = offer.getOfferHolder();
        Person responseHolder = offerResponse.getResponseHolder();

        offerHolder.getBackpack().putProduct(offerResponse.getResponseProduct());
        responseHolder.getBackpack().putProduct(offer.getOfferProduct());
    }

}
