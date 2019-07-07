package cft.shift.manasyan.barter.models.responses;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.user.User;

public class DesireResponse extends DealResponse {
    private Product desiredProductResponse;

    public DesireResponse(User responseHolder, Product desiredProduct) {
        super(responseHolder, desiredProduct);
    }

    @Override
    protected void registerResponse(){
        getResponseHolder().getUserResponses().addDesireResponse(this);
    }

    public void setDesiredProductResponse(Product desiredProductResponse) {
        this.desiredProductResponse = desiredProductResponse;
    }

    @Override
    public void accept(User dealOwner, Product dealOwnerProduct)
    {
        dealOwner.getBackpack().addProduct(getResponseProduct());
        getResponseHolder().getBackpack().addProduct(desiredProductResponse);
    }

    public Product getDesiredProductResponse() {
        return desiredProductResponse;
    }
}
