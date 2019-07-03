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

    @Autowired
    private DigitalBarterService digitalBarterService;

    @GetMapping(BARTER_PATH + "desires")
    public ResponseEntity<List<Offer>> getDesires(){
        return ResponseEntity.ok(digitalBarterService.getDesires());
    }

    @GetMapping(BARTER_PATH + "suggests")
    public ResponseEntity<List<Offer>> getSuggests(){
        return ResponseEntity.ok(digitalBarterService.getSuggests());
    }

    @GetMapping(BARTER_PATH + "/{userId}/backpack")
    public ResponseEntity<Backpack> getBackpack(@PathVariable String userId){
        Backpack backpack =  digitalBarterService.getPerson(userId).getBackpack();
        return ResponseEntity.ok(backpack);
    }

    @PostMapping (BARTER_PATH + "/{offerId}/desireResponseEvent")
    public ResponseEntity<?> handleDesireResponseEvent(
            @PathVariable String offerId,
            @RequestHeader("userId") String userId) {
        Person owner = digitalBarterService.getPerson(userId);
        Offer desire = digitalBarterService.getDesire(offerId);
        Product product = desire.getOfferHolderProduct();
        desire.registerOfferResponse(owner , product);

        return ResponseEntity.ok().build();
    }

    @PostMapping (BARTER_PATH + "/{offerId}/desireResponseEvent")
    public ResponseEntity<?> handleSuggestResponseEvent(
            @PathVariable String offerId,
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){
        Person owner = digitalBarterService.getPerson(userId);
        Offer suggestion = digitalBarterService.getSuggestion(offerId);
        Product product = owner.getBackpack().getProduct(productId);
        suggestion.registerOfferResponse(owner, product);

        return ResponseEntity.ok().build();
    }
}
