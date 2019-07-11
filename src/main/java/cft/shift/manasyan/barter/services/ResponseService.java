package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.models.deals.Offer;
import cft.shift.manasyan.barter.models.dtos.ResponseTO;
import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.responses.DesireResponse;
import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.repositories.DealRepository;
import cft.shift.manasyan.barter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

@Service
public class ResponseService {

    @Autowired
    private UserRepository users;

    @Autowired
    @Qualifier(value = "desires")
    private DealRepository<Desire> desiresRepository;

    @Autowired
    @Qualifier(value = "offers")
    private DealRepository<Offer> offersRepository;

    @Autowired
    private LoggingService loggingService;

    public ResponseEntity<ResponseTO> addDesireResponse(String desireId, String userId, String productId){
        return addDealResponse(desireId,userId,productId,desiresRepository);
    }

    public ResponseEntity<ResponseTO> addOfferResponse(String offerId, String userId, String productId){
        return addDealResponse(offerId,userId,productId,offersRepository);
    }

    public ResponseEntity<?> acceptDesire(String desireId, String responseId) {
        Desire desire = desiresRepository.getDeal(desireId);
        desiresRepository.removeDeal(desireId);
        desire.accept(responseId);
        desire.getDealHolder().getUserDeals().deleteOffer(desire);

        for(DealResponse response :  desire.getResponses().values()){
            User user = response.getResponseHolder();
            user.getUserResponses().deleteDesireResponse((DesireResponse) response);
        }

        loggingService.acceptDesireEvent(desireId, responseId);
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<?> discardOfferResponse(String dealId, String responseId) {
        return discardDealResponse(dealId,responseId,offersRepository);
    }

    public ResponseEntity<?> discardDesireResponse(String dealId, String responseId) {
        return discardDealResponse(dealId,responseId,desiresRepository);
    }

    public ResponseEntity<?> acceptOffer(String offerId, String responseId) {
        Offer offer = offersRepository.getDeal(offerId);
        offersRepository.removeDeal(offerId);
        offer.accept(responseId);
        offer.getDealHolder().getUserDeals().deleteOffer(offer);

        for(DealResponse response :  offer.getResponses().values()){
            User user = response.getResponseHolder();
            user.getUserResponses().deleteOfferResponse(response);
        }

        loggingService.acceptOfferEvent(offerId, responseId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> handleSecondDesireResponse(String productId, String desireId, String responseId){
        Desire desire = desiresRepository.getDeal(desireId);
        DesireResponse response = desire.getDealResponse(responseId);
        Product product = desire.getDealHolder().getBackpack().getAndDeleteProduct(productId);
        response.setDesiredProductResponse(product);

        return ResponseEntity.ok().build();
    }

    private  <T extends Deal> ResponseEntity<?> discardDealResponse(String dealId, String responseId, DealRepository<T> repository) {
        T deal = repository.getDeal(dealId);
        deal.getDealResponse(responseId).discard(deal.getDealHolder());
        deal.getResponses().remove(responseId);

        return ResponseEntity.ok().build();
    }

    private <T extends Deal> ResponseEntity<ResponseTO> addDealResponse(String desireId, String userId, String productId, DealRepository<T> repository ){
        User user = users.getUser(userId);
        T deal = repository.getDeal(desireId);
        Product product = user.getBackpack().getAndDeleteProduct(productId);

        DealResponse dealResponse = deal.registerResponse(user,product);
        return ResponseEntity.ok(new ResponseTO(dealResponse));
    }
}
