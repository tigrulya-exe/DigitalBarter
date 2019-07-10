package cft.shift.manasyan.barter.services;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.models.deals.Offer;
import cft.shift.manasyan.barter.models.dtos.*;
import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.repositories.DealRepository;
import cft.shift.manasyan.barter.repositories.UserRepository;
import cft.shift.manasyan.barter.services.helpers.DealPredicate;
import cft.shift.manasyan.barter.services.helpers.UserDealDeleter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static cft.shift.manasyan.barter.services.TOConstructorService.*;

import java.util.List;

@Service
public class DealService {

    @Autowired
    private UserRepository users;

    @Autowired
    private LoggingService loggingService;

    @Autowired
    @Qualifier(value = "desires")
    private DealRepository<Desire> desiresRepository;

    @Autowired
    @Qualifier(value = "offers")
    private DealRepository<Offer> offersRepository;

    public ResponseEntity<List<DealTO>> getDesires(String userId) {
        List<Desire> desires = constructRelevantList(desiresRepository.getDeals(), userId, this::isDesireRelevant);
        return ResponseEntity.ok(getDealTOs(desires));
    }

    public ResponseEntity<List<DealTO>> getOffers(String userId){
        List<Offer> offers = constructRelevantList(offersRepository.getDeals(), userId, this::isOfferRelevant);
        return ResponseEntity.ok(getDealTOs(offers));
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

    public ResponseEntity<DetailedDesireTO> deleteOffer(String offerId){
        deleteDeal(offerId,offersRepository, (user, deal) -> user.getUserDeals().deleteOffer(deal));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<DetailedDesireTO> deleteDesire(String offerId){
        deleteDeal(offerId,offersRepository, (user, deal) -> user.getUserDeals().deleteDesire(deal));
        return ResponseEntity.ok().build();
    }

    private <T extends Deal> void deleteDeal(String dealId, DealRepository<T> repository, UserDealDeleter deleter){
        T deal = repository.getDeal(dealId);
        Product product = deal.getDealProduct();
        User user = deal.getDealHolder();
        offersRepository.removeDeal(dealId);
        deal.close();

        deleter.delete(user,deal);

        user.getBackpack().putProduct(product);
    }

    private <T extends Deal> List<T> constructRelevantList(List<T> deals, String userId , DealPredicate predicate){
        User user = users.getUser(userId);
        Deal deal;

        for (Product product : user.getBackpack().getProducts()) {
            for (int i = 0; i < deals.size(); ++i) {
                deal = deals.get(i);
                if (predicate.isRelevant(deal,product)) {
                    deals.remove(i);
                    deals.add(0, (T) deal);
                }
            }
        }

        return deals;
    }

    private boolean isDesireRelevant(Deal desire, Product product){
        return desire.getDescription().contains(product.getName()) || desire.getDealProduct().getName().contains(product.getName());
    }

    private  boolean isOfferRelevant(Deal offer, Product product){
        return offer.getDescription().contains(product.getName());
    }

}
