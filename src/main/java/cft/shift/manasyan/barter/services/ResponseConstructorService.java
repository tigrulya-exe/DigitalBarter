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

    public ResponseEntity<List<Product>> getBackpack(String userId){

        Backpack backpack =  digitalBarterService.getPerson(userId).getBackpack();
        return ResponseEntity.ok(backpack.getProducts());
    }

    public ResponseEntity<List<ResponseTO>> getOfferResponses( String userId){

        List<ResponseTO> offerResponses = digitalBarterService.getOfferResponses(userId);
        return ResponseEntity.ok(offerResponses);
    }

    public ResponseEntity<UserTO> registerUser(String userName){

        User user = digitalBarterService.createUser(userName);
        loggingService.newUserEvent(user);
        return ResponseEntity.ok(new UserTO(user));
    }

    public ResponseEntity<ResponseTO> handleDesireResponse(String dealId, String userId, String productId){

        DealResponse response = digitalBarterService.handleDesireResponse(dealId,userId,productId);
        return ResponseEntity.ok(new ResponseTO(response));
    }

    public ResponseEntity<ResponseTO> handleOfferResponse(String dealId,String userId, String productId){

        DealResponse response =  digitalBarterService.handleOfferResponse(dealId,userId,productId);
        return ResponseEntity.ok(new ResponseTO(response));
    }

    public ResponseEntity<Product> putProductInBackpack(String userId, ProductTO productTO){

        Product product = digitalBarterService.putProductInBackpack(userId, productTO);
        loggingService.newProductEvent(userId, productTO);
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<?> acceptDesire(String dealId, String responseId){

        digitalBarterService.acceptDesire(dealId,responseId);
        loggingService.acceptOfferEvent(dealId,responseId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> acceptOffer(String dealId, String responseId){

        digitalBarterService.acceptOffer(dealId,responseId);
        loggingService.acceptOfferEvent(dealId,responseId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<DealTO> createDesire(String userId, DesireTO desireDTO){

        Deal desire =  digitalBarterService.createDesire(userId, desireDTO);
        loggingService.newDesireEvent(desire);
        return ResponseEntity.ok(new DealTO(desire));
    }

    public ResponseEntity<?> createOffer(String userId,OfferTO offerTO){
        Deal offer;
        try {
            offer = digitalBarterService.createOffer(userId, offerTO);
            loggingService.newOfferEvent(offer);
        }
        catch (NotFoundException nfe){
            return ResponseEntity.ok(nfe.getLocalizedMessage() + " not found");
        }
        return ResponseEntity.ok(new DealTO(offer));
    }

    public ResponseEntity<?> handleSecondDesireResponse(String productId, String dealId, String responseId){

        digitalBarterService.handleSecondDesireResponse(dealId,responseId,productId);
        return ResponseEntity.ok().build();
    }

}
