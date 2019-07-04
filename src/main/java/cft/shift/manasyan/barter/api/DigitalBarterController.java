package cft.shift.manasyan.barter.api;

import cft.shift.manasyan.barter.models.Backpack;
import cft.shift.manasyan.barter.models.Deal;
import cft.shift.manasyan.barter.models.User;
import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.dtos.*;
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

    @GetMapping(BARTER_PATH + "/desires")
    public ResponseEntity<List<Deal>> getDesires(){
        return ResponseEntity.ok(digitalBarterService.getDesires());
    }

    @GetMapping(BARTER_PATH + "/offers")
    public ResponseEntity<List<Deal>> getOffers(){
        return ResponseEntity.ok(digitalBarterService.getOffers());
    }

    @GetMapping(BARTER_PATH + "/{userId}/backpack")
    public ResponseEntity<List<Product>> getBackpack(@PathVariable String userId){

        Backpack backpack =  digitalBarterService.getPerson(userId).getBackpack();
        return ResponseEntity.ok(backpack.getProducts());
    }

    @PostMapping (BARTER_PATH + "/login")
    public ResponseEntity<LoginTO> registerUser(@RequestHeader("userName") String userName){

        User user = digitalBarterService.createUser(userName);
        return ResponseEntity.ok(new LoginTO(user.getUid()));
    }

    @PostMapping (BARTER_PATH + "/{dealId}/desireResponse")
    public ResponseEntity<?> handleDesireResponseEvent(
            @PathVariable String dealId,
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        digitalBarterService.handleDesireResponseEvent(dealId,userId,productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping (BARTER_PATH + "/{dealId}/offerResponse")
    public ResponseEntity<ReactionDTO> handleOfferResponseEvent(
            @PathVariable String dealId,
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        digitalBarterService.handleOfferResponseEvent(dealId,userId,productId);
        return ResponseEntity.ok().build();
    }

    @PostMapping (BARTER_PATH + "/{userId}/backpack")
    public ResponseEntity<Product> putProductInBackpack(
            @PathVariable String userId,
            @RequestBody ProductDTO productDTO){

        Product product = digitalBarterService.putProductInBackpack(userId,productDTO);
        return ResponseEntity.ok(product);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/acceptDesire")
    public ResponseEntity<?> acceptDesire(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        digitalBarterService.acceptDesire(dealId,responseId);
        return ResponseEntity.ok().build();
    }

    @PostMapping (BARTER_PATH + "/{dealId}/acceptOffer")
    public ResponseEntity<?> acceptOffer(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        digitalBarterService.acceptOffer(dealId,responseId);
        return ResponseEntity.ok().build();
    }

    @PostMapping (BARTER_PATH + "/desires")
    public ResponseEntity<DealTO> createDesire(
            @RequestHeader("userId") String userId,
            @RequestBody DesireDTO desireDTO){

        Deal desire =  digitalBarterService.createDesire(userId, desireDTO);
        return ResponseEntity.ok(new DealTO(desire));
    }

    @PostMapping (BARTER_PATH + "/offers")
    public ResponseEntity<DealTO> createOffer(
            @RequestHeader("userId") String userId,
            @RequestBody OfferDTO offerDTO){

        Deal offer =  digitalBarterService.createOffer(userId, offerDTO);
        return ResponseEntity.ok(new DealTO(offer));
    }

    //TODO handlers for desire/offer detailed view
}
