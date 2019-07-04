package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.*;
import cft.shift.manasyan.barter.models.dtos.DesireDTO;
import cft.shift.manasyan.barter.models.dtos.OfferDTO;
import cft.shift.manasyan.barter.models.dtos.ProductDTO;
import cft.shift.manasyan.barter.repositories.BarterDealRepository;
import cft.shift.manasyan.barter.repositories.BarterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DigitalBarterService {
    private BarterDealRepository desiresRepository;
    private BarterDealRepository offersRepository;

    private Map<String, User> users;

    @Autowired
    public DigitalBarterService(BarterDealRepository desiresRepository, BarterDealRepository offersRepository) {
        this.desiresRepository = desiresRepository;
        this.offersRepository = offersRepository;
        this.users = new HashMap<>();
    }

    public List<Deal> getDesires() {
        return desiresRepository.getDeals();
    }

    public List<Deal> getOffers() {
        return offersRepository.getDeals();
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public User getPerson(String userId){
        return users.get(userId);
    }

    public void acceptDesire(String dealId, String responseId){
        acceptDeal(dealId, responseId, desiresRepository);
    }

    public void acceptOffer(String dealId, String responseId){
        acceptDeal(dealId, responseId, offersRepository);
    }

    public Deal createOffer(String userId, OfferDTO offerDTO){
        User user = users.get(userId);
        Deal deal = new Deal(offerDTO, user);
        offersRepository.addDeal(deal);

        return deal;
    }

    public Deal createDesire(String userId, DesireDTO desireDTO) {
        User user = users.get(userId);
        Deal deal = new Deal(desireDTO,user);
        desiresRepository.addDeal(deal);

        return deal;
    }

    public User createUser(String userName){
        User user = new User(userName);
        addUser(user);
        return user;
    }

    public void addUser(User user){
        users.put(user.getUid(), user);
    }

    public void handleOfferResponseEvent(String dealId, String userId, String productId){
        handleResponseEvent(dealId,userId,productId,offersRepository);
    }

    //TODO check!!
    public void handleDesireResponseEvent(String dealId, String userId,String productId){
        handleResponseEvent(dealId,userId,productId,desiresRepository);
    }

    public Product putProductInBackpack(String userId, ProductDTO productDTO){
        return putProductInBackpack(userId,new Product(productDTO));
    }

    public Product putProductInBackpack(String userId, Product product){
        Backpack backpack =  users.get(userId).getBackpack();
        backpack.putProduct(product);

        return product;
    }

    private void handleResponseEvent(String dealId, String userId,String productId, BarterDealRepository repository){
        User owner = users.get(userId);
        Deal desire = repository.getDeal(dealId);
        Product product = desire.getDealProduct();
        desire.registerDealResponse(owner , product);
    }

    private void acceptDeal(String dealId, String responseId, BarterDealRepository barterDealRepository){
        Deal deal = barterDealRepository.getDeal(dealId);
        barterDealRepository.closeDeal(dealId);
        deal.closeDeal(responseId);
    }

}
