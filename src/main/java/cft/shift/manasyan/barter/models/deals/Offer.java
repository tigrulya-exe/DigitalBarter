package cft.shift.manasyan.barter.models.deals;

import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.models.dtos.OfferTO;
import lombok.NonNull;

import java.util.HashMap;

public class Offer extends Deal {
//    private HashMap<String, DealResponse> responses = null;/*list of responses to current offer*/

    public Offer(OfferTO offerTO, @NonNull User user) {
        super(user.getBackpack().getProduct(offerTO.getProductId()), user,  offerTO.getDescription());
    }

    @Override
    protected DealResponse createDealResponse(@NonNull User user, @NonNull Product product) {
        return new DealResponse(user, product);
    }

//    @Override
//    protected DealResponse createDealResponse(User user, Product product) {
//        return new DealResponse(user,product);
//    }

}
