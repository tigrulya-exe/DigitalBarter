package cft.shift.manasyan.barter.api;

import cft.shift.manasyan.barter.models.Backpack;
import cft.shift.manasyan.barter.models.Offer;
import cft.shift.manasyan.barter.models.Person;
import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.services.DigitalBarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DigitalBarterController {
    private static final String BARTER_PATH = "/api/v001/barter";

    private class LoginHelper {
        private String userId;

        LoginHelper(String userId) {
            this.userId = userId;
        }
    }

    @Autowired
    private DigitalBarterService digitalBarterService;

    @GetMapping(BARTER_PATH + "/desires")
    public ResponseEntity<List<Offer>> getDesires(){
        return ResponseEntity.ok(digitalBarterService.getDesires());
    }

    @GetMapping(BARTER_PATH + "/suggestions")
    public ResponseEntity<List<Offer>> getSuggests(){
        return ResponseEntity.ok(digitalBarterService.getSuggests());
    }

    @GetMapping(BARTER_PATH + "/{userId}/backpack")
    public ResponseEntity<Backpack> getBackpack(@PathVariable String userId){

        Backpack backpack =  digitalBarterService.getPerson(userId).getBackpack();
        return ResponseEntity.ok(backpack);
    }

    @PostMapping (BARTER_PATH + "/login")
    public ResponseEntity<LoginHelper> registerUser(@RequestHeader("userName") String userName){

        Person user = digitalBarterService.createUser(userName);
        return ResponseEntity.ok(new LoginHelper(user.getUid()));
    }

    @PostMapping (BARTER_PATH + "/{offerId}/desireResponse")
    public ResponseEntity<?> handleDesireResponseEvent(
            @PathVariable String offerId,
            @RequestHeader("userId") String userId) {

        digitalBarterService.handleDesireResponseEvent(offerId,userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping (BARTER_PATH + "/{offerId}/suggestResponse")
    public ResponseEntity<?> handleSuggestResponseEvent(
            @PathVariable String offerId,
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        digitalBarterService.handleSuggestResponseEvent(offerId,userId,productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping (BARTER_PATH + "/{userId}/backpack")
    public ResponseEntity<Product> putProductInBackpack(
            @PathVariable String userId,
            @RequestBody Product product){

        digitalBarterService.putProductInBackpack(userId,product);
        return ResponseEntity.ok(product);
    }

    @PostMapping (BARTER_PATH + "/{offerId}/acceptDesire")
    public ResponseEntity<?> acceptDesire(
            @PathVariable String offerId,
            @RequestHeader("responseId") String responseId){

        digitalBarterService.acceptDesire(offerId,responseId);
        return ResponseEntity.ok().build();
    }

    //TODO check!!
    @PostMapping (BARTER_PATH + "/{offerId}/acceptSuggest")
    public ResponseEntity<?> acceptSuggest(
            @PathVariable String offerId,
            @RequestHeader("responseId") String responseId){

        digitalBarterService.acceptSuggestion(offerId,responseId);
        return ResponseEntity.ok().build();
    }

    @PostMapping (BARTER_PATH + "/desires")
    public ResponseEntity<Offer> createDesire(
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        Offer desire =  digitalBarterService.createDesire(userId, productId);
        return ResponseEntity.ok(desire);
    }

    @PostMapping (BARTER_PATH + "/suggestions")
    public ResponseEntity<Offer> createSuggestion(
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        Offer suggestion =  digitalBarterService.createSuggestion(userId, productId);
        return ResponseEntity.ok(suggestion);
    }

    //TODO handlers for desire/suggestion detailed view
}
