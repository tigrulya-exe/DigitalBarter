package cft.shift.manasyan.barter.models;

public class OfferResponse {
    private Person responseHolder;
    private Product responseProduct;
    private String id;
    /*One offer can have only one response by one person. Thus response id is equal to holder id.*/
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

    public void accept(Person offerOwner, Product offerOwnerProduct)
    {
        offerOwner.getBackpack().addProduct(responseProduct);
        responseProduct = null;
        responseHolder.getBackpack().addProduct(offerOwnerProduct);
    }

    public void discard(){
        responseHolder.getBackpack().addProduct(responseProduct);
    }
}
