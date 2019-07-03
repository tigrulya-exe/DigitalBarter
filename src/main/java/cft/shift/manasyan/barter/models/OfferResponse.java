package cft.shift.manasyan.barter.models;

public class OfferResponse {
    private Person responseHolder;
    private Product responseProduct;
    private String id;

    public OfferResponse(Person responseHolder, Product responseProduct) {
        this.responseHolder = responseHolder;
        this.responseProduct = responseProduct;
        this.id = responseHolder.getUid();
    }

    public Person getResponseHolder() {
        return responseHolder;
    }

    public Product getResponseProduct() {
        return responseProduct;
    }

    public String getId() {
        return id;
    }

    public void accept(){};

    public void discard(){
        responseHolder.putInBackpack(responseProduct);
    }
}
