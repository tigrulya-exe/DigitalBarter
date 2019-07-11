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
import cft.shift.manasyan.barter.repositories.databases.interfaces.ProductRepository;
import cft.shift.manasyan.barter.repositories.databases.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static cft.shift.manasyan.barter.services.TOConstructorService.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    @Qualifier("sql")
    private UserRepository users;

    @Autowired
    private LoggingService loggingService;

    @Autowired
    @Qualifier("sqlProducts")
    private ProductRepository products;

    public ResponseEntity<List<Product>> getBackpack(String userId){
        Backpack backpack = users.getUser(userId).getBackpack();

        //List<Product> backpack = products.getUserProducts(userId);
        return ResponseEntity.ok(backpack.getProducts());
    }

    public ResponseEntity<UserTO> registerUser(String userName){
        //if(users.contains(userName))
        //    throw new WrongUserNameException("User with this name already exists");

        User user = new User(userName);
        users.addUser(user);
        return ResponseEntity.ok(new UserTO(user));
    }

    public ResponseEntity<List<DealTO>> getUserOffers(String userId) {
        User user = users.getUser(userId);
        List<DealTO> offers = getDealTOs(user.getUserDeals().getOffers());
        return ResponseEntity.ok(offers);
    }

    public ResponseEntity<?> deleteProduct(String userId, String productId) {
        User user = users.getUser(userId);
        user.getBackpack().deleteProduct(productId);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<DealTO>> getUserDesires(String userId) {
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
        product.setUserID(userId);
        user.getBackpack().putProduct(product, userId);

        loggingService.newProductEvent(userId,product);
        return ResponseEntity.ok(product);
    }
}
