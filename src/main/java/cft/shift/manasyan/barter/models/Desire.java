package cft.shift.manasyan.barter.models;

import cft.shift.manasyan.barter.models.dtos.DesireDTO;

import java.util.HashMap;
import java.util.Map;

public class Desire extends Deal {
    public Desire(DesireDTO desireDTO, User user)
    {
        super(new Product(desireDTO.getProduct()), user, desireDTO.getDescription());
        getDealHolder().getUserDeals().addDesire(this);
    }

    @Override
    public  DesireResponse getDealResponse(String responseId){
        return (DesireResponse) getResponses().get(responseId);
    }

    @Override
    protected DesireResponse createDealResponse(User user, Product product) {
        return new DesireResponse(user,product);
    }

}
