package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.*;
import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.models.deals.Offer;
import cft.shift.manasyan.barter.models.dtos.DealTO;
import cft.shift.manasyan.barter.models.dtos.DesireTO;
import cft.shift.manasyan.barter.models.dtos.OfferTO;
import cft.shift.manasyan.barter.models.dtos.ProductTO;
import cft.shift.manasyan.barter.models.responses.DesireResponse;
import cft.shift.manasyan.barter.models.user.Backpack;
import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.repositories.BarterDealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DigitalBarterService {

    @Autowired
    @Qualifier(value = "desires")
    private BarterDealRepository<Desire> desiresRepository;

    @Autowired
    @Qualifier(value = "offers")
    private BarterDealRepository<Offer> offersRepository;

    private Map<String, User> users;

    @Autowired
    public DigitalBarterService() {
        this.users = new HashMap<>();
    }

    public List<Desire> getDesires() {
        return desiresRepository.getDeals();
    }

    public List<Offer> getOffers() {
        return offersRepository.getDeals();
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public User getPerson(String userId){
        return users.get(userId);
    }

    public void acceptDesire(String dealId, String responseId){
        Desire desire = desiresRepository.getDeal(dealId);
        desiresRepository.closeDeal(dealId);
        desire.closeDeal(responseId);
    }

    public void acceptOffer(String dealId, String responseId){
        Offer offer = offersRepository.getDeal(dealId);
        offersRepository.closeDeal(dealId);
        offer.closeDeal(responseId);
    }

    public Deal createOffer(String userId, OfferTO offerTO){
        User user = users.get(userId);
        Offer offer = new Offer(offerTO, user);
        offersRepository.addDeal(offer);

        return offer;
    }

    public Desire createDesire(String userId, DesireTO desireDTO) {
        User user = users.get(userId);
        Desire desire = new Desire(desireDTO,user);
        desiresRepository.addDeal(desire);

        return desire;
    }

    public User createUser(String userName){
        User user = new User(userName);
        addUser(user);
        return user;
    }

    public void addUser(User user){
        users.put(user.getUid(), user);
    }

    public String handleOfferResponse(String dealId, String userId, String productId){
        return handleResponse(dealId,userId,productId,offersRepository);
    }


    public String handleDesireResponse(String dealId, String userId, String productId){
        return handleResponse(dealId,userId,productId,desiresRepository);
    }

    public Product putProductInBackpack(String userId, ProductTO productTO){
        return putProductInBackpack(userId,new Product(productTO));
    }

    public void handleSecondDesireResponse(String desireId, String responseId, String productId){
        Desire desire = desiresRepository.getDeal(desireId);
        DesireResponse response = desire.getDealResponse(responseId);
        Product product = desire.getDealHolder().getBackpack().getProduct(productId);
        response.setDesiredProductResponse(product);
    }

    public Product putProductInBackpack(String userId, Product product){
        Backpack backpack =  users.get(userId).getBackpack();
        backpack.putProduct(product);

        return product;
    }

    public List<DealTO> getOfferDTOs() {
        return getDTOs(offersRepository);
    }

    public List<DealTO> getDesireDTOs() {
        return getDTOs(desiresRepository);
    }

    private List<DealTO> getDTOs(BarterDealRepository<?> repository){
        List<DealTO> desireDTOS = new ArrayList<>();

        for(Deal desire : repository.getDeals()){
            desireDTOS.add(new DealTO(desire));
        }
        return desireDTOS;
    }

    private String handleResponse(String offerId, String userId, String productId, BarterDealRepository<?> repository){
        User responder = users.get(userId);
        Deal deal = repository.getDeal(offerId);
        Product product = responder.getBackpack().getProduct(productId);

        return deal.registerResponse(responder , product);
    }

}
