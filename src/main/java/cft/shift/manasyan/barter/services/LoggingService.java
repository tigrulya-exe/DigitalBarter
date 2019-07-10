package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.api.UserController;
import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.user.User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@Service
public class LoggingService {
    private Logger logger;
    private static final String PROPERTIES_PATH = "logging.properties";

    public LoggingService(){
        InputStream stream = UserController.class.getClassLoader().
                getResourceAsStream(PROPERTIES_PATH);
        try {
            LogManager.getLogManager().readConfiguration(stream);
            logger = Logger.getLogger(UserController.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void newUserEvent(User user){
        logger.info("User " + user.getName() + " with id [" + user.getId() + "] was created");
    }

    public void newProductEvent(String userId, Product product){
        logger.info("User with id [" + userId + "] added " + product.getName());
    }

    public void newDesireEvent(Deal desire){
        logger.info("User [" + desire.getDealHolder().getName() + "] added desire "
                + desire.getDealProduct().getName() + " with id[" + desire.getDealProduct().getId() + "]");
    }

    public void newOfferEvent(Deal offer){
        logger.info("User [" + offer.getDealHolder().getName() + "] added offer "
                + offer.getDealProduct().getName() + " with id[" + offer.getDealProduct().getId() + "]");
    }

    public void acceptOfferEvent(String offerId, String responseId){
        logger.info("Offer [" + offerId + "] accepted by response [" + responseId + "]");
    }

    public void acceptDesireEvent(String desireId, String responseId){
        logger.info("Desire [" + desireId + "] accepted by response [" + responseId + "]");
    }
}
