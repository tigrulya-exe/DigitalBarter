package cft.shift.manasyan.barter.api;

import cft.shift.manasyan.barter.models.user.Backpack;
import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.dtos.*;
import cft.shift.manasyan.barter.services.DigitalBarterService;
import cft.shift.manasyan.barter.services.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DigitalBarterController {
    private static final String BARTER_PATH = "/api/v001/barter";

    @Autowired
    private LoggingService loggingService;
    @Autowired
    private DigitalBarterService digitalBarterService;

    @GetMapping(BARTER_PATH + "/desires")
    public ResponseEntity<List<DealTO>> getDesires(){
        return ResponseEntity.ok(digitalBarterService.getDesireDTOs());
    }

    @GetMapping(BARTER_PATH + "/offers")
    public ResponseEntity<List<DealTO>> getOffers(){
        digitalBarterService.getOfferDTOs();
        return ResponseEntity.ok(digitalBarterService.getOfferDTOs());
    }

    @GetMapping(BARTER_PATH + "/{userId}/backpack")
    public ResponseEntity<List<Product>> getBackpack(@PathVariable String userId){

        Backpack backpack =  digitalBarterService.getPerson(userId).getBackpack();
        return ResponseEntity.ok(backpack.getProducts());
    }

    @PostMapping (BARTER_PATH + "/login")
    public ResponseEntity<LoginTO> registerUser(@RequestHeader("userName") String userName){

        User user = digitalBarterService.createUser(userName);
        loggingService.newUserEvent(user);
        return ResponseEntity.ok(new LoginTO(user.getUid()));
    }

    @PostMapping (BARTER_PATH + "/{dealId}/desireResponse")
    public ResponseEntity<ReactionDTO> handleDesireResponse(
            @PathVariable String dealId,
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        String responseId = digitalBarterService.handleDesireResponse(dealId,userId,productId);
        return ResponseEntity.ok(new ReactionDTO(responseId));
    }

    @PostMapping (BARTER_PATH + "/{dealId}/offerResponse")
    public ResponseEntity<ReactionDTO> handleOfferResponse(
            @PathVariable String dealId,
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        String responseId =  digitalBarterService.handleOfferResponse(dealId,userId,productId);
        return ResponseEntity.ok(new ReactionDTO(responseId));
    }

    @PostMapping (BARTER_PATH + "/{userId}/backpack")
    public ResponseEntity<Product> putProductInBackpack(
            @PathVariable String userId,
            @RequestBody ProductDTO productDTO){

        Product product = digitalBarterService.putProductInBackpack(userId,productDTO);
        loggingService.newProductEvent(userId,productDTO);
        return ResponseEntity.ok(product);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/acceptDesire")
    public ResponseEntity<?> acceptDesire(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        digitalBarterService.acceptDesire(dealId,responseId);
        loggingService.acceptOfferEvent(dealId,responseId);
        return ResponseEntity.ok().build();
    }

    @PostMapping (BARTER_PATH + "/{dealId}/acceptOffer")
    public ResponseEntity<?> acceptOffer(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        digitalBarterService.acceptOffer(dealId,responseId);
        loggingService.acceptOfferEvent(dealId,responseId);
        return ResponseEntity.ok().build();
    }

    @PostMapping (BARTER_PATH + "/desires")
    public ResponseEntity<DealTO> createDesire(
            @RequestHeader("userId") String userId,
            @RequestBody DesireDTO desireDTO){

        Deal desire =  digitalBarterService.createDesire(userId, desireDTO);
        loggingService.newDesireEvent(desire);
        return ResponseEntity.ok(new DealTO(desire));
    }

    @PostMapping (BARTER_PATH + "/offers")
    public ResponseEntity<DealTO> createOffer(
            @RequestHeader("userId") String userId,
            @RequestBody OfferDTO offerDTO){

        Deal offer =  digitalBarterService.createOffer(userId, offerDTO);
        loggingService.newOfferEvent(offer);
        return ResponseEntity.ok(new DealTO(offer));
    }

    @PostMapping (BARTER_PATH + "/{dealId}/{responseId}/desireResponse")
    public ResponseEntity<?> handleSecondDesireResponse(
            @RequestHeader("productId") String productId,
            @PathVariable String dealId,
            @PathVariable String responseId){

        digitalBarterService.handleSecondDesireResponse(dealId,responseId,productId);
        return ResponseEntity.ok().build();
    }
    //TODO handlers for desire/offer detailed view
}
