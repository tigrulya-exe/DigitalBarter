package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.responses.DesireResponse;

public class DesireResponseTO extends ResponseTO{
    private Product desiredProductResponse;

    public DesireResponseTO(DesireResponse desireResponse){
        super(desireResponse);
        this.desiredProductResponse = desireResponse.getDesiredProductResponse();
    }

    public Product getDesiredProductResponse() {
        return desiredProductResponse;
    }

    public void setDesiredProductResponse(Product desiredProductResponse) {
        this.desiredProductResponse = desiredProductResponse;
    }
}