package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.*;
import cft.shift.manasyan.barter.repositories.BarterDealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DigitalBarterService {
    private BarterDealRepository desiresRepository;
    private BarterDealRepository suggestionsRepository;

    private Map<String, User> persons;

    @Autowired
    public DigitalBarterService(BarterDealRepository desiresRepository, BarterDealRepository suggestionsRepository) {
        this.desiresRepository = desiresRepository;
        this.suggestionsRepository = suggestionsRepository;
        this.persons = new HashMap<>();
    }

    public List<Deal> getDesires() {
        return desiresRepository.getDeals();
    }

    public List<Deal> getSuggests() {
        return suggestionsRepository.getDeals();
    }

    public List<User> getPersons() {
        return new ArrayList<>(persons.values());
    }

    public User getPerson(String userId){
        return persons.get(userId);
    }

    public void acceptDesire(String dealId, String responseId){
        acceptDeal(dealId, responseId, desiresRepository);
    }

    public void acceptSuggestion(String dealId, String responseId){
        acceptDeal(dealId, responseId, suggestionsRepository);
    }

    public Deal createSuggestion(String userId, String productId){
        Deal suggestion = createDealOffer(userId, productId);

        //TODO сделать покрасивше
        suggestion.getDealHolder().getUserDeals().addSuggestion(suggestion);
        suggestionsRepository.addDeal(suggestion);

        return suggestion;
    }

    public Deal createDesire(String userId, String productId) {
        Deal desire = createDealDesire(userId, productId);

        //TODO сделать покрасивше
        desire.getDealHolder().getUserDeals().addDesire(desire);
        desiresRepository.addDeal(desire);

        return desire;
    }

    public User createUser(String userName){
        User user = new User(userName);
        addUser(user);
        return user;
    }

    public void addUser(User user){
        persons.put(user.getUid(), user);
    }

    public void handleSuggestResponseEvent(String dealId, String userId, String productId){
        User owner = persons.get(userId) ;
        Deal suggestion = suggestionsRepository.getDeal(dealId);
        Product product = owner.getBackpack().getProduct(productId);
        suggestion.registerDealResponse(owner, product);
    }

    //TODO check!!
    public void handleDesireResponseEvent(String dealId, String userId){
        User owner = persons.get(userId);
        Deal desire = desiresRepository.getDeal(dealId);
        Product product = desire.getDealProduct();
        desire.registerDealResponse(owner , product);
    }

    public void putProductInBackpack(String userId, Product product){
        Backpack backpack =  persons.get(userId).getBackpack();
        backpack.putProduct(product);
    }

    private Deal createDealOffer(String userId, String productId){
        User user = persons.get(userId);
        Product product = user.getBackpack().getProduct(productId);
        return new Deal(product, user, Deal.DealType.OFFER);/*TODO add description*/
    }

    private Deal createDealDesire(String userId, String productId){
        User user = persons.get(userId);
        Product product = user.getBackpack().getProduct(productId);
        return new Deal(product, user, Deal.DealType.DESIRE);/*TODO add description*/
    }

    private void acceptDeal(String dealId, String responseId, BarterDealRepository barterDealRepository){
        Deal deal = barterDealRepository.getDeal(dealId);
        DealResponse dealResponse = deal.getDealResponse(responseId);
        barterDealRepository.closeDeal(dealId);
        deal.closeDeal(responseId);
    }

}
