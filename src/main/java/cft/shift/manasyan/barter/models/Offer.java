package cft.shift.manasyan.barter.models;

import cft.shift.manasyan.barter.models.dtos.OfferDTO;

import java.util.HashMap;

public class Offer extends Deal {
    private HashMap<String, OfferResponse> responses = null;/*list of responses to current offer*/

    public Offer(OfferDTO offerDTO, User user)
    {
        super(user.getBackpack().getProduct(offerDTO.getProductId()), user,  offerDTO.getDescription());
        getDealHolder().getUserDeals().addOffer(this);
    }

    @Override
    protected OfferResponse createDealResponse(User user, Product product) {
        return new OfferResponse(user,product);
    }

}
