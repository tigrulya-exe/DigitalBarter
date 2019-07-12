package cft.shift.manasyan.barter.api;
import cft.shift.manasyan.barter.models.dtos.*;
import cft.shift.manasyan.barter.services.DealService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "Получение всех желаний")
    public ResponseEntity<List<DealTO>> getDesires(
            @RequestHeader("userId") String userId){
        return dealService.getDesires(userId);
    }

    @GetMapping(BARTER_PATH + "/offers")
    @ApiOperation(value = "Получение всех предложений")
    public ResponseEntity<List<DealTO>> getOffers(
            @RequestHeader("userId") String userId){
        return dealService.getOffers(userId);
    }

    @PostMapping(BARTER_PATH + "/desires")
    @ApiOperation(value = "Создание желания")
    public ResponseEntity<DealTO> createDesire(
            @RequestHeader("userId") String userId,
            @RequestBody DesireTO desireDTO){

        return dealService.createDesire(userId,desireDTO);
    }

    @PostMapping (BARTER_PATH + "/offers")
    @ApiOperation(value = "Создание предложения")
    public ResponseEntity<DealTO> createOffer(
            @RequestHeader("userId") String userId,
            @RequestBody OfferTO offerTO){

        return dealService.createOffer(userId,offerTO);
    }

    @GetMapping (BARTER_PATH + "/offers/{offerId}")
    @ApiOperation(value = "Получение подробной информации о предложении")
    public ResponseEntity<DetailedDealTO<?>> getDetailedOffer(
            @PathVariable String offerId){

        return dealService.getDetailedOffer(offerId);
    }

    @GetMapping (BARTER_PATH + "/desires/{desireId}")
    @ApiOperation(value = "Получение подробной информации о желании")
    public ResponseEntity<DetailedDesireTO> getDetailedDesire(
            @PathVariable String desireId) {

        return dealService.getDetailedDesire(desireId);
    }

    @DeleteMapping (BARTER_PATH + "/desires/{desireId}")
    @ApiOperation(value = "Удаление желания")
    public ResponseEntity<?> deleteDesire(
            @PathVariable String desireId) {

        return dealService.deleteDesire(desireId);
    }

    @DeleteMapping(BARTER_PATH + "/offers/{offerId}")
    @ApiOperation(value = "Удаление предложения")
    public ResponseEntity<?> deleteOffer(
            @PathVariable String offerId) {

        return dealService.deleteOffer(offerId);
    }
}
