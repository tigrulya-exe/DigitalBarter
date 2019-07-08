package cft.shift.manasyan.barter.api;
import cft.shift.manasyan.barter.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponseController {

    private static final String BARTER_PATH = "/api/v001/barter";

    @Autowired
    private ResponseService responseService;

    @PostMapping(BARTER_PATH + "/{dealId}/desireResponse")
    public ResponseEntity<?> addDesireResponse(
            @PathVariable String dealId,
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        return responseService.addDesireResponse(dealId,userId,productId);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/offerResponse")
    public ResponseEntity<?> addOfferResponse(
            @PathVariable String dealId,
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        return responseService.addOfferResponse(dealId,userId,productId);
    }


    @PostMapping (BARTER_PATH + "/{dealId}/acceptDesire")
    public ResponseEntity<?> acceptDesire(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        return responseService.acceptDesire(dealId,responseId);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/acceptOffer")
    public ResponseEntity<?> acceptOffer(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        return responseService.acceptOffer(dealId,responseId);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/{responseId}/desireResponse")
    public ResponseEntity<?> handleSecondDesireResponse(
            @RequestHeader("productId") String productId,
            @PathVariable String dealId,
            @PathVariable String responseId){

        return responseService.handleSecondDesireResponse(productId,dealId,responseId);
    }
}
