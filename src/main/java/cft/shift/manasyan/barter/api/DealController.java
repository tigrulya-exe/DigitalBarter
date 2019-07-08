package cft.shift.manasyan.barter.api;
import cft.shift.manasyan.barter.models.dtos.DealTO;
import cft.shift.manasyan.barter.models.dtos.DesireTO;
import cft.shift.manasyan.barter.models.dtos.OfferTO;
import cft.shift.manasyan.barter.services.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DealController {
    private static final String BARTER_PATH = "/api/v001/barter";

    @Autowired
    private DealService dealService;

    @GetMapping(BARTER_PATH + "/desires")
    public ResponseEntity<List<DealTO>> getDesires(){
        return dealService.getDesires();
    }

    @GetMapping(BARTER_PATH + "/offers")
    public ResponseEntity<List<DealTO>> getOffers(){
        return dealService.getOffers();
    }

    @PostMapping(BARTER_PATH + "/desires")
    public ResponseEntity<?> createDesire(
            @RequestHeader("userId") String userId,
            @RequestBody DesireTO desireDTO){

        return dealService.createDesire(userId,desireDTO);
    }

    @PostMapping (BARTER_PATH + "/offers")
    public ResponseEntity<?> createOffer(
            @RequestHeader("userId") String userId,
            @RequestBody OfferTO offerTO){

        return dealService.createOffer(userId,offerTO);
    }

    @GetMapping (BARTER_PATH + "/offers/{offerId}")
    public ResponseEntity<?> getDetailedOffer(
            @PathVariable String offerId){

        return dealService.getDetailedOffer(offerId);
    }

    @GetMapping (BARTER_PATH + "/desires/{desireId}")
    public ResponseEntity<?> getDetailedDesire(
            @PathVariable String desireId) {

        return dealService.getDetailedDesire(desireId);
    }
}
