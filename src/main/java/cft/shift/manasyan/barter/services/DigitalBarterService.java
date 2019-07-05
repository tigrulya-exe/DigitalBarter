package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.*;
import cft.shift.manasyan.barter.models.dtos.DealTO;
import cft.shift.manasyan.barter.models.dtos.DesireDTO;
import cft.shift.manasyan.barter.models.dtos.OfferDTO;
import cft.shift.manasyan.barter.models.dtos.ProductDTO;
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
    private BarterDealRepository desiresRepository;

    @Autowired
    @Qualifier(value = "offers")
    private BarterDealRepository offersRepository;

    private Map<String, User> users;

    @Autowired
    public DigitalBarterService() {
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

    public String handleOfferResponse(String dealId, String userId, String productId){
        return handleResponse(dealId,userId,productId,offersRepository);
    }


    public String handleDesireResponse(String dealId, String userId, String productId){
        return handleResponse(dealId,userId,productId,desiresRepository);
    }

    public Product putProductInBackpack(String userId, ProductDTO productDTO){
        return putProductInBackpack(userId,new Product(productDTO));
    }

    public void handleSecondDesireResponse(String dealId, String responseId, String productId){
        Deal deal = desiresRepository.getDeal(dealId);
        DesireResponse response = (DesireResponse) deal.getDealResponse(responseId);
        Product product = deal.getDealHolder().getBackpack().getProduct(productId);
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

    private List<DealTO> getDTOs(BarterDealRepository repository){
        List<DealTO> desireDTOS = new ArrayList<>();

        for(Deal desire : repository.getDeals()){
            desireDTOS.add(new DealTO(desire));
        }

        return desireDTOS;
    }

    private String handleResponse(String dealId, String userId, String productId, BarterDealRepository repository){
        User responder = users.get(userId);
        Deal deal = repository.getDeal(dealId);
        Product product = responder.getBackpack().getProduct(productId);

        return deal.registerDealResponse(responder , product);
    }

    private void acceptDeal(String dealId, String responseId, BarterDealRepository barterDealRepository){
        Deal deal = barterDealRepository.getDeal(dealId);
        barterDealRepository.closeDeal(dealId);
        deal.closeDeal(responseId);
    }
}
