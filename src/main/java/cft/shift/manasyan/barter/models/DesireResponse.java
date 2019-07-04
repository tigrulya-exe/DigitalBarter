package cft.shift.manasyan.barter.models;

public class DesireResponse extends DealResponse  {
    private Product desiredProductResponse;

    public DesireResponse(User responseHolder, Product desiredProduct) {
        super(responseHolder, desiredProduct);
    }

    public void setDesiredProductResponse(Product desiredProductResponse) {
        this.desiredProductResponse = desiredProductResponse;
    }

    public Product getDesiredProductResponse() {
        return desiredProductResponse;
    }
}
