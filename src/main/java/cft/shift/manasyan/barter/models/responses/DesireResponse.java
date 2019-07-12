package cft.shift.manasyan.barter.models.responses;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.user.User;

public class DesireResponse extends DealResponse {
    private Product desiredProductResponse;

    public DesireResponse(User responseHolder, Product desiredProduct) {
        super(responseHolder, desiredProduct);
    }

    public DesireResponse(User responseHolder, Product responseProduct, Product desiredProductResponse) {
        super(responseHolder, responseProduct);
        this.desiredProductResponse = desiredProductResponse;
    }

    @Override
    protected void registerResponse(){
        getResponseHolder().getUserResponses().addDesireResponse(this);
    }

    public void setDesiredProductResponse(Product desiredProductResponse) {
        this.desiredProductResponse = desiredProductResponse;
    }

    @Override
    public void accept(User dealOwner, Product dealOwnerProduct) {
        dealOwner.getBackpack().putProduct(getResponseProduct(), dealOwner.getId());
        getResponseHolder().getBackpack().putProduct(desiredProductResponse, getResponseHolder().getId());
    }

    public Product getDesiredProductResponse() {
        return desiredProductResponse;
    }

    public void discard(User dealOwner){
        super.discard(dealOwner);
        if (desiredProductResponse != null)
            dealOwner.getBackpack().putProduct(desiredProductResponse, getResponseHolder().getId());
    }

}
