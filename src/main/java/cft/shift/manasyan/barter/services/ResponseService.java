package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.models.deals.Offer;
import cft.shift.manasyan.barter.models.dtos.ResponseTO;
import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.responses.DesireResponse;
import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.repositories.databases.interfaces.DealRepository;
import cft.shift.manasyan.barter.repositories.databases.interfaces.ProductRepository;
import cft.shift.manasyan.barter.repositories.databases.interfaces.ResponseRepository;
import cft.shift.manasyan.barter.repositories.databases.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseService {

    @Autowired
    @Qualifier("sql")
    private UserRepository users;

//    @Autowired
//    @Qualifier(value = "desires")
//    private DealRepository<Desire> desiresRepository;
//
//    @Autowired
//    @Qualifier(value = "offers")
//    private DealRepository<Offer> offersRepository;
    @Autowired
    @Qualifier("sqlProducts")
    private ProductRepository products;

    @Autowired
    @Qualifier("sqlDesires")
    private DealRepository<Desire>  desiresRepository;

    @Autowired
    @Qualifier("sqlOffers")
    private DealRepository<Offer>  offersRepository;

    @Autowired
    @Qualifier("desireResponses")
    private ResponseRepository<DesireResponse> desireResponses;

    @Autowired
    @Qualifier("offerResponses")
    private ResponseRepository<DealResponse> offerResponses;

    @Autowired
    private LoggingService loggingService;

    public ResponseEntity<ResponseTO> addDesireResponse(String desireId, String userId, String productId){
        User user = users.getUser(userId);
//        Product product = user.getBackpack().getAndDeleteProduct(productId);
        Product product = products.getProduct(productId);
        product.setUserID("0");
        products.updateProduct(product);

        DesireResponse response = desireResponses.createResponse(new DesireResponse(user,product), desireId);
        return ResponseEntity.ok(new ResponseTO(response));
    }

    public ResponseEntity<ResponseTO> addOfferResponse(String offerId, String userId, String productId){
        User user = users.getUser(userId);
        //TODO add product repo
//        Product product = user.getBackpack().getAndDeleteProduct(productId);
        Product product = products.getProduct(productId);
        product.setUserID("0");
        products.updateProduct(product);

        DealResponse dealResponse = offerResponses.createResponse(new DealResponse(user, product), offerId);
        return ResponseEntity.ok(new ResponseTO(dealResponse));
    }

    public ResponseEntity<?> acceptDesire(String desireId, String responseId) {
        Desire desire = desiresRepository.getDeal(desireId);
        desiresRepository.removeDeal(desireId);
        desire.closeDeal(responseId);

        loggingService.acceptDesireEvent(desireId, responseId);
        return ResponseEntity.ok().build();
    }


    public ResponseEntity<?> acceptOffer(String offerId, String responseId) {
        Offer offer = offersRepository.getDeal(offerId);
        offersRepository.removeDeal(offerId);

        List<Product> userProducts = products.getUserProducts(offer.)

        loggingService.acceptOfferEvent(offerId, responseId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> acceptOffer(String offerId, String responseId) {
        offersRepository.removeDeal(offerId);
        Offer offer = offersRepository.getDeal(offerId);
        offersRepository.removeDeal(offerId);
        offer.closeDeal(responseId);

        loggingService.acceptOfferEvent(offerId, responseId);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> handleSecondDesireResponse(String productId, String desireId, String responseId){
        Desire desire = desiresRepository.getDeal(desireId);
        DesireResponse response = desire.getDealResponse(responseId);
        Product product = desire.getDealHolder().getBackpack().getAndDeleteProduct(productId);
        response.setDesiredProductResponse(product);

        return ResponseEntity.ok().build();
    }


}


