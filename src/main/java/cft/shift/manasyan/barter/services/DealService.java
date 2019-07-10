package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.models.deals.Offer;
import cft.shift.manasyan.barter.models.dtos.*;
import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.repositories.databases.disk.DatabaseDesireRepository;
import cft.shift.manasyan.barter.repositories.databases.disk.DatabaseOfferRepository;
import cft.shift.manasyan.barter.repositories.databases.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static cft.shift.manasyan.barter.services.TOConstructorService.*;

import java.util.List;

@Service
public class DealService {

    @Autowired
    @Qualifier("sql")
    private UserRepository users;

    @Autowired
    private LoggingService loggingService;

//    @Autowired
//    @Qualifier(value = "desires")
//    private DealRepository<Desire> desiresRepository;
//
//    @Autowired
//    @Qualifier(value = "offers")
//    private DealRepository<Offer> offersRepository;

    @Autowired
    private DatabaseDesireRepository desiresRepository;

    @Autowired
    private DatabaseOfferRepository offersRepository;

    public ResponseEntity<List<DealTO>> getDesires(){
        return ResponseEntity.ok(getDealTOs(desiresRepository.getDeals()));
    }

    public ResponseEntity<List<DealTO>> getOffers(){
        return ResponseEntity.ok(getDealTOs(offersRepository.getDeals()));
    }

    public ResponseEntity<DealTO> createDesire(String userId, DesireTO desireDTO) {
        User user = users.getUser(userId);
        Desire desire = new Desire(desireDTO,user);
        user.getUserDeals().addDesire(desire);

        desiresRepository.addDeal(desire);
        loggingService.newDesireEvent(desire);

        return ResponseEntity.ok(new DealTO(desire));
    }

    public ResponseEntity<DealTO> createOffer(String userId, OfferTO offerTO) {
        User user = users.getUser(userId);
        Offer offer = new Offer(offerTO,user);

        user.getUserDeals().addOffer(offer);
        user.getBackpack().deleteProduct(offerTO.getProductId());

        offersRepository.addDeal(offer);
        loggingService.newOfferEvent(offer);

        return ResponseEntity.ok(new DealTO(offer));
    }

    public ResponseEntity<DetailedDealTO<?>> getDetailedOffer(String offerId) {
        Offer offer = offersRepository.getDeal(offerId);
        DetailedDealTO<?> detailedDealTO = new DetailedDealTO<>(offer);
        return ResponseEntity.ok(detailedDealTO);
    }

    public ResponseEntity<DetailedDesireTO> getDetailedDesire(String desireId) {
        Desire desire = desiresRepository.getDeal(desireId);
        DetailedDesireTO  detailedDesireTO = new  DetailedDesireTO(desire);
        return ResponseEntity.ok(detailedDesireTO);
    }

}
