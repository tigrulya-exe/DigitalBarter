package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.dtos.DealTO;
import cft.shift.manasyan.barter.models.dtos.DesireResponseTO;
import cft.shift.manasyan.barter.models.dtos.ResponseTO;
import cft.shift.manasyan.barter.models.dtos.UserTO;
import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.responses.DesireResponse;
import cft.shift.manasyan.barter.models.user.Backpack;
import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static cft.shift.manasyan.barter.services.TOConstructorService.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository users;

    @Autowired
    private LoggingService loggingService;

    public ResponseEntity<List<Product>> getBackpack(String userId){
        Backpack backpack = users.getUser(userId).getBackpack();
        return ResponseEntity.ok(backpack.getProducts());
    }

    public ResponseEntity<UserTO> registerUser(String userName){
        User user = new User(userName);
        users.addUser(user);
        return ResponseEntity.ok(new UserTO(user));
    }

    public ResponseEntity<List<DealTO>> getUserOffers(String userId) {
        User user = users.getUser(userId);
        List<DealTO> offers = getDealTOs(user.getUserDeals().getOffers());
        return ResponseEntity.ok(offers);
    }

    public ResponseEntity<?> getUserDesires(String userId) {
        User user = users.getUser(userId);
        List<DealTO> offers = getDealTOs(user.getUserDeals().getDesires());
        return ResponseEntity.ok(offers);
    }

    public ResponseEntity<List<ResponseTO>> getOfferResponses( String userId) {
        User user = users.getUser(userId);
        List<ResponseTO> responseTOs = new ArrayList<>();

        for(DealResponse response : user.getOfferResponses()){
            responseTOs.add(new ResponseTO(response));
        }

        return ResponseEntity.ok(responseTOs);
    }

    public ResponseEntity<List<ResponseTO>> getDesireResponses( String userId) {
        User user = users.getUser(userId);
        List<ResponseTO> responseTOs = new ArrayList<>();

        for(DesireResponse response : user.getDesireResponses()){
            responseTOs.add(new DesireResponseTO(response));
        }
        return ResponseEntity.ok(responseTOs);
    }

    public ResponseEntity<Product> putProductInBackpack(String userId, Product product) {
        User user = users.getUser(userId);
        user.getBackpack().putProduct(product);

        loggingService.newProductEvent(userId,product);
        return ResponseEntity.ok(product);
    }
}
