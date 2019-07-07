package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.*;
import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.models.deals.Offer;
import cft.shift.manasyan.barter.models.dtos.*;
import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.responses.DesireResponse;
import cft.shift.manasyan.barter.models.user.Backpack;
import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.repositories.BarterDealRepository;
import cft.shift.manasyan.barter.repositories.DealRepository;
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

    public User getUser(String userId) throws NotFoundException{
        User user =  users.get(userId);
        if (user == null)
            throw new NotFoundException("Wrong userId");
        return users.get(userId);
    }

    public void acceptDesire(String dealId, String responseId){
        Desire desire = getDeal(desiresRepository,dealId);
        desiresRepository.closeDeal(dealId);
        desire.closeDeal(responseId);
    }

    public void acceptOffer(String dealId, String responseId){
        Offer offer = getDeal(offersRepository, dealId);
        offersRepository.closeDeal(dealId);
        offer.closeDeal(responseId);
    }

    public Deal createOffer(String userId, OfferTO offerTO) {
        User user = getUser(userId);
        Offer offer = new Offer(offerTO, user);
        offersRepository.addDeal(offer);

        return offer;
    }

    public Desire createDesire(String userId, DesireTO desireDTO) {
        User user = getUser(userId);
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

    public DealResponse handleOfferResponse(String dealId, String userId, String productId){
        return handleResponse(dealId,userId,productId,offersRepository);
    }

    public DesireResponse handleDesireResponse(String dealId, String userId, String productId){
        return (DesireResponse) handleResponse(dealId,userId,productId,desiresRepository);
    }

    public Product putProductInBackpack(String userId, ProductTO productTO){
        return putProductInBackpack(userId,new Product(productTO));
    }

    public void handleSecondDesireResponse(String desireId, String responseId, String productId){
        Desire desire = getDeal(desiresRepository, desireId);
        DesireResponse response = desire.getDealResponse(responseId);
        Product product = desire.getDealHolder().getBackpack().getAndDeleteProduct(productId);
        response.setDesiredProductResponse(product);
    }

    public Product putProductInBackpack(String userId, Product product){
        User user = getUser(userId);
        user.getBackpack().putProduct(product);

        return product;
    }

    public List<DealTO> getOfferTOs() {
        return getDealTOs(offersRepository.getDeals());
    }

    public List<DealTO> getDesireTOs() {
        return getDealTOs(desiresRepository.getDeals());
    }

    public Product getProductInfo(String userId, String productId) {
        User user = getUser(userId);
        return user.getBackpack().getProduct(productId);
    }

    public List<ResponseTO> getOfferResponses(String userId){
        User user = getUser(userId);
        List<ResponseTO> responseTOs = new ArrayList<>();

        for(DealResponse response : user.getOfferResponses()){
            responseTOs.add(new ResponseTO(response));
        }

        return responseTOs;
    }

    public List<ResponseTO> getDesireResponses(String userId){
        User user = getUser(userId);
        List<ResponseTO> responseTOs = new ArrayList<>();

        for(DesireResponse response : user.getDesireResponses()){
            responseTOs.add(new DesireResponseTO(response));
        }

        return responseTOs;
    }

    public  <T extends Deal> List<DealTO> getDealTOs(List<T> deals){
        List<DealTO> dealTOS = new ArrayList<>();

        for(Deal deal : deals){
            dealTOS.add(new DealTO(deal));
        }
        return dealTOS;
    }

    private DealResponse handleResponse(String offerId, String userId, String productId, BarterDealRepository<?> repository){
        User responder = getUser(userId);
        Deal deal = getDeal(repository, offerId);
        Product product = responder.getBackpack().getAndDeleteProduct(productId);

        return deal.registerResponse(responder , product);
    }

    private <T extends Deal> T getDeal(DealRepository<T> repository, String dealId) throws NotFoundException{
        T deal = repository.getDeal(dealId);
        if(deal == null)
            throw new NotFoundException("Wrong deal id");
        return deal;
    }
}
