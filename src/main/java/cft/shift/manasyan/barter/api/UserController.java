package cft.shift.manasyan.barter.api;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.dtos.DealTO;
import cft.shift.manasyan.barter.models.dtos.ResponseTO;
import cft.shift.manasyan.barter.models.dtos.UserTO;
import cft.shift.manasyan.barter.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private static final String BARTER_PATH = "/api/v001/barter";

    @Autowired
    UserService userService;

    @PostMapping (BARTER_PATH + "/login")
    @ApiOperation(value = "Добавление нового пользователя")
    public ResponseEntity<UserTO> registerUser(
            @ApiParam(value = "Имя пользователя")
            @RequestHeader("userName") String userName){
        return userService.registerUser(userName);
    }

    @GetMapping(BARTER_PATH + "/{userId}/offers")
    @ApiOperation(value = "Получение предложений пользователя")
    public ResponseEntity<List<DealTO>> getUserOffers(@PathVariable String userId){
        return userService.getUserOffers(userId);
    }

    @GetMapping(BARTER_PATH + "/{userId}/desires")
    @ApiOperation(value = "Получение желаний пользователя")
    public ResponseEntity<List<DealTO>> getUserDesires(@PathVariable String userId){
        return userService.getUserDesires(userId);
    }

    @GetMapping(BARTER_PATH + "/{userId}/backpack")
    @ApiOperation(value = "Получение рюкзака пользователя")
    public ResponseEntity<List<Product>> getBackpack(@PathVariable String userId){
        return userService.getBackpack(userId);
    }

    @GetMapping(BARTER_PATH + "/{userId}/offerResponses")
    @ApiOperation(value = "Получение реакций пользователя на предложения")
    public ResponseEntity<List<ResponseTO>> getOfferResponses(@PathVariable String userId){
        return userService.getOfferResponses(userId);
    }

    @GetMapping(BARTER_PATH + "/{userId}/desireResponses")
    @ApiOperation(value = "Получение реакций пользователя на желания")
    public ResponseEntity<List<ResponseTO>> getDesireResponses(@PathVariable String userId){
        return userService.getDesireResponses(userId);
    }

    @PostMapping(BARTER_PATH + "/{userId}/backpack")
    @ApiOperation(value = "Добавление предмета в рюкзак")
    public ResponseEntity<Product> putProductInBackpack(
            @PathVariable String userId,
            @RequestBody Product product){

        return userService.putProductInBackpack(userId,product);
    }

    @DeleteMapping(BARTER_PATH + "/{userId}/{productId}")
    @ApiOperation(value = "Удаление предмета из рюкзака")
    public ResponseEntity<?> deleteProduct(
            @PathVariable String userId,
            @PathVariable String  productId){

        return userService.deleteProduct(userId,productId);
    }

}
