package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.*;
import cft.shift.manasyan.barter.models.dtos.DealTO;
import cft.shift.manasyan.barter.models.dtos.DesireDTO;
import cft.shift.manasyan.barter.models.dtos.OfferDTO;
import cft.shift.manasyan.barter.models.dtos.ProductDTO;
import cft.shift.manasyan.barter.repositories.BarterDealRepository;
import cft.shift.manasyan.barter.repositories.BarterDesireRepository;
import cft.shift.manasyan.barter.repositories.BarterOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DigitalBarterService {

    @Autowired
    @Qualifier(value = "desires")
    private BarterDesireRepository desiresRepository;

    @Autowired
    @Qualifier(value = "offers")
    private BarterOfferRepository offersRepository;

    private Map<String, User> users;

    @Autowired
    public DigitalBarterService() {
        this.users = new HashMap<>();
    }

    public List<Desire> getDesires() {
        return desiresRepository.getDesires();
    }

    public List<Offer> getOffers() {
        return offersRepository.getOffers();
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public User getPerson(String userId){
        return users.get(userId);
    }

    public void acceptDesire(String dealId, String responseId){
        Desire desire = barterDesireRepository.getDesire(desireId);
        barterDesireRepository.closeDesire(desireId);
        desire.closeDesire(responseId);
    }

    public void acceptOffer(String dealId, String responseId){
        Offer offer = barterOfferRepository.getOffer(offerId);
        barterOfferRepository.closeOffer(offerId);
        offer.closeOffer(responseId);
    }

    public Deal createOffer(String userId, OfferDTO offerDTO){
        User user = users.get(userId);
        Offer offer = new Offer(offerDTO, user);
        offersRepository.addOffer(offer);

        return offer;
    }

    public Desire createDesire(String userId, DesireDTO desireDTO) {
        User user = users.get(userId);
        Desire desire = new Desire(desireDTO,user);
        desiresRepository.addDesire(desire);

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

    public Product putProductInBackpack(String userId, ProductDTO productDTO){
        return putProductInBackpack(userId,new Product(productDTO));
    }

    public void handleSecondDesireResponse(String desireId, String responseId, String productId){
        Desire desire = desiresRepository.getDesire(desireId);
        DesireResponse response = (DesireResponse) desire.getDesireResponse(responseId);
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

    private List<DealTO> getDTOs(BarterDealRepository repository){
        List<DealTO> desireDTOS = new ArrayList<>();

        for(Deal desire : repository.getDeals()){
            desireDTOS.add(new DealTO(desire));
        }
        return desireDTOS;
    }

//    private String handleResponse(String dealId, String userId, String productId, BarterDealRepository repository){
//        User owner = users.get(userId);
//        Deal desire = repository.getDeal(dealId);
//        Product product = desire.getDealProduct();
//
//        return desire.registerDealResponse(owner , product);
//    }
//
    private String handleResponse(String offerId, String userId, String productId, BarterOfferRepository repository){
        User owner = users.get(userId);
        Offer offer = repository.getOffer(offerId);
        Product product = offer.getDealProduct();

        return offer.registerOfferResponse(owner , product);
    }

    private String handleResponse(String desireId, String userId, String productId, BarterDesireRepository repository){
        User owner = users.get(userId);
        Desire desire = repository.getDesire(desireId);
        Product product = desire.getDealProduct();
        return desire.registerDesireResponse(owner , product);
    }

//    private void acceptDeal(String dealId, String responseId, BarterDealRepository barterDealRepository){
//        Deal deal = barterDealRepository.getDeal(dealId);
//        barterDealRepository.closeDeal(dealId);
//        deal.closeDeal(responseId);
//    }
}
