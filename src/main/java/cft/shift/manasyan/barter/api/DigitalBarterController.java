package cft.shift.manasyan.barter.api;

import cft.shift.manasyan.barter.models.Backpack;
import cft.shift.manasyan.barter.models.Deal;
import cft.shift.manasyan.barter.models.User;
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
    public ResponseEntity<List<Deal>> getDesires(){
        return ResponseEntity.ok(digitalBarterService.getDesires());
    }

    @GetMapping(BARTER_PATH + "/suggestions")
    public ResponseEntity<List<Deal>> getSuggests(){
        return ResponseEntity.ok(digitalBarterService.getSuggests());
    }

    @GetMapping(BARTER_PATH + "/{userId}/backpack")
    public ResponseEntity<List<Product>> getBackpack(@PathVariable String userId){

        Backpack backpack =  digitalBarterService.getPerson(userId).getBackpack();
        return ResponseEntity.ok(backpack.getProducts());
    }

    @PostMapping (BARTER_PATH + "/login")
    public ResponseEntity<LoginHelper> registerUser(@RequestHeader("userName") String userName){

        User user = digitalBarterService.createUser(userName);
        return ResponseEntity.ok(new LoginHelper(user.getUid()));
    }

    @PostMapping (BARTER_PATH + "/{dealId}/desireResponse")
    public ResponseEntity<?> handleDesireResponseEvent(
            @PathVariable String dealId,
            @RequestHeader("userId") String userId) {

        digitalBarterService.handleDesireResponseEvent(dealId,userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping (BARTER_PATH + "/{dealId}/suggestResponse")
    public ResponseEntity<?> handleSuggestResponseEvent(
            @PathVariable String dealId,
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        digitalBarterService.handleSuggestResponseEvent(dealId,userId,productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping (BARTER_PATH + "/{userId}/backpack")
    public ResponseEntity<Product> putProductInBackpack(
            @PathVariable String userId,
            @RequestBody Product product){

        digitalBarterService.putProductInBackpack(userId,product);
        return ResponseEntity.ok(product);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/acceptDesire")
    public ResponseEntity<?> acceptDesire(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        digitalBarterService.acceptDesire(dealId,responseId);
        return ResponseEntity.ok().build();
    }

    //TODO check!!
    @PostMapping (BARTER_PATH + "/{dealId}/acceptSuggest")
    public ResponseEntity<?> acceptSuggest(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        digitalBarterService.acceptSuggestion(dealId,responseId);
        return ResponseEntity.ok().build();
    }

    @PostMapping (BARTER_PATH + "/desires")
    public ResponseEntity<Deal> createDesire(
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        Deal desire =  digitalBarterService.createDesire(userId, productId);
        return ResponseEntity.ok(desire);
    }

    @PostMapping (BARTER_PATH + "/suggestions")
    public ResponseEntity<Deal> createSuggestion(
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        Deal suggestion =  digitalBarterService.createSuggestion(userId, productId);
        return ResponseEntity.ok(suggestion);
    }

    //TODO handlers for desire/suggestion detailed view
}
