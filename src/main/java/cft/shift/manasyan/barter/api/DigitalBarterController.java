package cft.shift.manasyan.barter.api;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.dtos.*;
import cft.shift.manasyan.barter.services.ResponseConstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DigitalBarterController {
    private static final String BARTER_PATH = "/api/v001/barter";

    @Autowired
    private ResponseConstructorService responseConstructorService;

    @GetMapping(BARTER_PATH + "/desires")
    public ResponseEntity<List<DealTO>> getDesires(){
        return responseConstructorService.getDesires();
    }

    @GetMapping(BARTER_PATH + "/offers")
    public ResponseEntity<List<DealTO>> getOffers(){
        return responseConstructorService.getOffers();
    }

    @GetMapping(BARTER_PATH + "/{userId}/offers")
    public ResponseEntity<?> getUserOffers(@PathVariable String userId){
        return responseConstructorService.getUserOffers(userId);
    }

    @GetMapping(BARTER_PATH + "/{userId}/desires")
    public ResponseEntity<?> getUserDesires(@PathVariable String userId){
        return responseConstructorService.getUserDesires(userId);
    }

    @GetMapping(BARTER_PATH + "/{userId}/backpack")
    public ResponseEntity<?> getBackpack(@PathVariable String userId){
        return responseConstructorService.getBackpack(userId);
    }

    @GetMapping(BARTER_PATH + "/{userId}/offerResponses")
    public ResponseEntity<?> getOfferResponses(@PathVariable String userId){
        return responseConstructorService.getOfferResponses(userId);
    }

    @GetMapping(BARTER_PATH + "/{userId}/desireResponses")
    public ResponseEntity<?> getDesireResponses(@PathVariable String userId){
        return responseConstructorService.getDesireResponses(userId);
    }

    @GetMapping(BARTER_PATH + "/{userId}/{productId}")
    public ResponseEntity<?> getProductInfo(
            @PathVariable String userId,
            @PathVariable String productId){
        return responseConstructorService.getProductInfo(userId, productId);
    }

    @PostMapping (BARTER_PATH + "/login")
    public ResponseEntity<UserTO> registerUser(@RequestHeader("userName") String userName){
        return responseConstructorService.registerUser(userName);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/desireResponse")
    public ResponseEntity<?> handleDesireResponse(
            @PathVariable String dealId,
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        return responseConstructorService.handleDesireResponse(dealId,userId,productId);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/offerResponse")
    public ResponseEntity<?> handleOfferResponse(
            @PathVariable String dealId,
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        return responseConstructorService.handleOfferResponse(dealId,userId,productId);
    }

    @PostMapping (BARTER_PATH + "/{userId}/backpack")
    public ResponseEntity<?> putProductInBackpack(
            @PathVariable String userId,
            @RequestBody ProductTO productTO){

        return responseConstructorService.putProductInBackpack(userId,productTO);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/acceptDesire")
    public ResponseEntity<?> acceptDesire(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        return responseConstructorService.acceptDesire(dealId,responseId);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/acceptOffer")
    public ResponseEntity<?> acceptOffer(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        return responseConstructorService.acceptOffer(dealId,responseId);
    }

    @PostMapping (BARTER_PATH + "/desires")
    public ResponseEntity<?> createDesire(
            @RequestHeader("userId") String userId,
            @RequestBody DesireTO desireDTO){

        return responseConstructorService.createDesire(userId,desireDTO);
    }

    @PostMapping (BARTER_PATH + "/offers")
    public ResponseEntity<?> createOffer(
            @RequestHeader("userId") String userId,
            @RequestBody OfferTO offerTO){

        return responseConstructorService.createOffer(userId,offerTO);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/{responseId}/desireResponse")
    public ResponseEntity<?> handleSecondDesireResponse(
            @RequestHeader("productId") String productId,
            @PathVariable String dealId,
            @PathVariable String responseId){

        return responseConstructorService.handleSecondDesireResponse(productId,dealId,responseId);
    }
    //TODO handlers for desire/offer detailed view
}
