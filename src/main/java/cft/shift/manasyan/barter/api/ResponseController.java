package cft.shift.manasyan.barter.api;
import cft.shift.manasyan.barter.models.dtos.ResponseTO;
import cft.shift.manasyan.barter.services.ResponseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResponseController {

    private static final String BARTER_PATH = "/api/v001/barter";

    @Autowired
    private ResponseService responseService;

    @PostMapping(BARTER_PATH + "/{dealId}/desireResponse")
    @ApiOperation(value = "Добавление реакции на желание")
    public ResponseEntity<ResponseTO> addDesireResponse(
            @PathVariable String dealId,
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        return responseService.addDesireResponse(dealId,userId,productId);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/offerResponse")
    @ApiOperation(value = "Добавление реакции на предложение")
    public ResponseEntity<ResponseTO> addOfferResponse(
            @PathVariable String dealId,
            @RequestHeader("userId") String userId,
            @RequestHeader("productId") String productId){

        return responseService.addOfferResponse(dealId,userId,productId);
    }


    @PostMapping (BARTER_PATH + "/{dealId}/acceptDesire")
    @ApiOperation(value = "Завершение(закрытие) желания")
    public ResponseEntity<?> acceptDesire(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        return responseService.acceptDesire(dealId,responseId);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/acceptOffer")
    @ApiOperation(value = "Завершение(закрытие) предложения")
    public ResponseEntity<?> acceptOffer(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        return responseService.acceptOffer(dealId,responseId);
    }

    @PostMapping (BARTER_PATH + "/{dealId}/{responseId}/desireResponse")
    @ApiOperation(value = "Добавление реакции обладателя желания на реакцию на это желание")
    public ResponseEntity<?> handleSecondDesireResponse(
            @RequestHeader("productId") String productId,
            @PathVariable String dealId,
            @PathVariable String responseId){

        return responseService.handleSecondDesireResponse(productId,dealId,responseId);
    }

    @DeleteMapping (BARTER_PATH + "/{dealId}/discardOfferResponse")
    @ApiOperation(value = "Отклонение реакции на предложение")
    public ResponseEntity<?> discardOfferResponse(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        return responseService.discardOfferResponse(dealId,responseId);
    }

    @DeleteMapping (BARTER_PATH + "/{dealId}/discardDesireResponse")
    @ApiOperation(value = "Отклонение реакции на желание")
    public ResponseEntity<?> discardDesireResponse(
            @PathVariable String dealId,
            @RequestHeader("responseId") String responseId){

        return responseService.discardDesireResponse(dealId,responseId);
    }
}
