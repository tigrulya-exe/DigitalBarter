package cft.shift.manasyan.barter.api;

import cft.shift.manasyan.barter.models.Backpack;
import cft.shift.manasyan.barter.models.OfferGet;
import cft.shift.manasyan.barter.models.OfferGive;
import cft.shift.manasyan.barter.services.DigitalBarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DigitalBarterController {
    private static final String BARTER_PATH = "/    api/v001/barter";

    @Autowired
    private DigitalBarterService digitalBarterService;



    @GetMapping(BARTER_PATH + "/desires")
    public ResponseEntity<List<OfferGet>> getDesires(
            @RequestHeader("userName") String userName
    ){
        return ResponseEntity.ok(digitalBarterService.getDesires());
    }

    @GetMapping(BARTER_PATH + "/suggests")
    public ResponseEntity<List<OfferGive>> getSuggests(){
        return ResponseEntity.ok(digitalBarterService.getSuggests());
    }

    @GetMapping(BARTER_PATH + "/backpack/{userId}")
    public ResponseEntity<Backpack> getBackpack(@PathVariable String userName){
        return null;
    }

}
