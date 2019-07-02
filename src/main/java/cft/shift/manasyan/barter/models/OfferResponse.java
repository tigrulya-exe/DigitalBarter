package cft.shift.manasyan.barter.models;

public class OfferResponse {
    private Person responseHolder;
    private Product responseProduct;
    private boolean isActive;

    public OfferResponse(Person responseHolder, Product responseProduct) {
        this.responseHolder = responseHolder;
        this.responseProduct = responseProduct;
    }

    public Person getResponseHolder() {
        return responseHolder;
    }

    public Product getResponseProduct() {
        return responseProduct;
    }

    public void discard(){
        responseHolder.putInBackpack(responseProduct);
    }


}
