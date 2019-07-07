package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.dtos.*;
import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.user.Backpack;
import cft.shift.manasyan.barter.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseConstructorService {

    @Autowired
    private DigitalBarterService digitalBarterService;
    @Autowired
    private LoggingService loggingService;

    public ResponseEntity<List<DealTO>> getDesires(){
        return ResponseEntity.ok(digitalBarterService.getDesireTOs());
    }

    public ResponseEntity<List<DealTO>> getOffers(){
        digitalBarterService.getOfferTOs();
        return ResponseEntity.ok(digitalBarterService.getOfferTOs());
    }

    public ResponseEntity<?> getBackpack(String userId){
        try {
            Backpack backpack = digitalBarterService.getUser(userId).getBackpack();
            return ResponseEntity.ok(backpack.getProducts());
        }
        catch (NotFoundException nfe){
            return constructErrorTO(nfe);
        }
    }

    public ResponseEntity<?> getOfferResponses( String userId){
        try {
            List<ResponseTO> offerResponses = digitalBarterService.getOfferResponses(userId);
            return ResponseEntity.ok(offerResponses);
        }
        catch (NotFoundException nfe){
            return constructErrorTO(nfe);
        }
    }

    public ResponseEntity<?> getDesireResponses( String userId){
        try {
            List<ResponseTO> desireResponses = digitalBarterService.getDesireResponses(userId);
            return ResponseEntity.ok(desireResponses);
        }
        catch (NotFoundException nfe){
            return constructErrorTO(nfe);
        }
    }

    public ResponseEntity<UserTO> registerUser(String userName){

        User user = digitalBarterService.createUser(userName);
        loggingService.newUserEvent(user);
        return ResponseEntity.ok(new UserTO(user));
    }

    public ResponseEntity<?> handleDesireResponse(String dealId, String userId, String productId){
        try {
            DealResponse response = digitalBarterService.handleDesireResponse(dealId, userId, productId);
            return ResponseEntity.ok(new ResponseTO(response));
        }
        catch (NotFoundException nfe){
            return constructErrorTO(nfe);
        }
    }

    public ResponseEntity<?> getUserOffers(String userId) {
        return getUserDeals(userId,(user)->user.getUserDeals().getOffers());
    }

    public ResponseEntity<?> getUserDesires(String userId) {
        return getUserDeals(userId,(user)->user.getUserDeals().getDesires());
    }

    public ResponseEntity<?> handleOfferResponse(String dealId,String userId, String productId){
        try {
            DealResponse response =  digitalBarterService.handleOfferResponse(dealId,userId,productId);
            return ResponseEntity.ok(new ResponseTO(response));
        }
        catch (NotFoundException nfe){
            return constructErrorTO(nfe);
        }
    }

    public ResponseEntity<?> putProductInBackpack(String userId, ProductTO productTO){
        try {
            Product product = digitalBarterService.putProductInBackpack(userId, productTO);
            loggingService.newProductEvent(userId, productTO);
            return ResponseEntity.ok(product);
        } catch (NotFoundException nfe){
            return constructErrorTO(nfe);
        }
    }

    public ResponseEntity<?> acceptDesire(String dealId, String responseId){
        try {
            digitalBarterService.acceptDesire(dealId,responseId);
            loggingService.acceptOfferEvent(dealId,responseId);
            return ResponseEntity.ok().build();
        }
        catch (NotFoundException nfe){
            return constructErrorTO(nfe);
        }
    }

    public ResponseEntity<?> acceptOffer(String dealId, String responseId) {
        try {
            digitalBarterService.acceptOffer(dealId, responseId);
            loggingService.acceptOfferEvent(dealId, responseId);
            return ResponseEntity.ok().build();
        }
        catch (NotFoundException nfe){
            return constructErrorTO(nfe);
        }
    }

    public ResponseEntity<?> createDesire(String userId, DesireTO desireDTO) {
        try {
            Deal desire = digitalBarterService.createDesire(userId, desireDTO);
            loggingService.newDesireEvent(desire);
            return ResponseEntity.ok(new DealTO(desire));
        }
        catch (NotFoundException nfe){
            return constructErrorTO(nfe);
        }
    }

    public ResponseEntity<?> createOffer(String userId,OfferTO offerTO){
        try {
            Deal offer = digitalBarterService.createOffer(userId, offerTO);
            loggingService.newOfferEvent(offer);
            return ResponseEntity.ok(new DealTO(offer));
        }
        catch (NotFoundException nfe){
            return constructErrorTO(nfe);
        }
    }

    public ResponseEntity<?> handleSecondDesireResponse(String productId, String dealId, String responseId){

        digitalBarterService.handleSecondDesireResponse(dealId,responseId,productId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getProductInfo(String userId, String productId) {
        try {
            Product product = digitalBarterService.getProductInfo(userId,productId);
            return ResponseEntity.ok(product);
        }
        catch (NotFoundException nfe){
            return constructErrorTO(nfe);
        }
    }

    private ResponseEntity<ErrorTO> constructErrorTO(NotFoundException nfe){
        return ResponseEntity.ok(new ErrorTO(nfe.getLocalizedMessage()));
    }

    private ResponseEntity<?> getUserDeals(String userId, DealsSupplier dealsSupplier) {
        try {
            User user = digitalBarterService.getUser(userId);
            List<DealTO> offers = digitalBarterService.getDealTOs(dealsSupplier.getDeals(user));
            return ResponseEntity.ok(offers);
        }
        catch (NotFoundException nfe){
            return constructErrorTO(nfe);
        }
    }
}

@FunctionalInterface
interface DealsSupplier{
    List<Deal> getDeals(User user);
}
