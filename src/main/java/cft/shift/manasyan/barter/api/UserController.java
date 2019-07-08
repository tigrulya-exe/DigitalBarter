package cft.shift.manasyan.barter.api;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.dtos.UserTO;
import cft.shift.manasyan.barter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private static final String BARTER_PATH = "/api/v001/barter";

    @Autowired
    UserService userService;

    @PostMapping (BARTER_PATH + "/login")
    public ResponseEntity<UserTO> registerUser(@RequestHeader("userName") String userName){
        return userService.registerUser(userName);
    }

    @GetMapping(BARTER_PATH + "/{userId}/offers")
    public ResponseEntity<?> getUserOffers(@PathVariable String userId){
        return userService.getUserOffers(userId);
    }

    @GetMapping(BARTER_PATH + "/{userId}/desires")
    public ResponseEntity<?> getUserDesires(@PathVariable String userId){
        return userService.getUserDesires(userId);
    }

    @GetMapping(BARTER_PATH + "/{userId}/backpack")
    public ResponseEntity<?> getBackpack(@PathVariable String userId){
        return userService.getBackpack(userId);
    }

    @GetMapping(BARTER_PATH + "/{userId}/offerResponses")
    public ResponseEntity<?> getOfferResponses(@PathVariable String userId){
        return userService.getOfferResponses(userId);
    }

    @GetMapping(BARTER_PATH + "/{userId}/desireResponses")
    public ResponseEntity<?> getDesireResponses(@PathVariable String userId){
        return userService.getDesireResponses(userId);
    }

    @PostMapping(BARTER_PATH + "/{userId}/backpack")
    public ResponseEntity<?> putProductInBackpack(
            @PathVariable String userId,
            @RequestBody Product product){

        return userService.putProductInBackpack(userId,product);
    }

}
