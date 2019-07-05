package cft.shift.manasyan.barter.models.deals;

import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.models.dtos.OfferDTO;

import java.util.HashMap;

public class Offer extends Deal {
    private HashMap<String, DealResponse> responses = null;/*list of responses to current offer*/

    public Offer(OfferDTO offerDTO, User user)
    {
        super(user.getBackpack().getProduct(offerDTO.getProductId()), user,  offerDTO.getDescription());
        getDealHolder().getUserDeals().addOffer(this);
    }

    @Override
    protected DealResponse createDealResponse(User user, Product product) {
        return new DealResponse(user,product);
    }

}
