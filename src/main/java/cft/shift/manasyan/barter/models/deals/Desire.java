package cft.shift.manasyan.barter.models.deals;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.responses.DesireResponse;
import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.models.dtos.DesireTO;

public class Desire extends Deal {
    public Desire(DesireTO desireDTO, User user) {
        super(new Product(desireDTO.getProduct()), user, desireDTO.getDescription());
        getDealHolder().getUserDeals().addDesire(this);
    }

    @Override
    public DesireResponse getDealResponse(String responseId){
        DesireResponse desireResponse = (DesireResponse) getResponses().get(responseId);
        if(desireResponse == null)
            throw new NotFoundException("Wrong desireId");
        return desireResponse;
    }

    @Override
    protected DesireResponse createDealResponse(User user, Product product) {
        return new DesireResponse(user,product);
    }

}
