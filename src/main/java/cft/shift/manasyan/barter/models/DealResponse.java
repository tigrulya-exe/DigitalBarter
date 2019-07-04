package cft.shift.manasyan.barter.models;

public class DealResponse {
    private User responseHolder;
    private Product responseProduct;
    private String id;
    /*One deal can have only one response by one person. Thus response id is equal to holder id.*/
    public DealResponse(User responseHolder, Product responseProduct) {
        this.responseHolder = responseHolder;
        this.responseProduct = responseProduct;
        this.id = responseHolder.getUid();
    }

    public User getResponseHolder() {
        return responseHolder;
    }

    public Product getResponseProduct() {
        return responseProduct;
    }

    public String getId() {
        return id;
    }

    public void accept(User dealOwner, Product dealOwnerProduct)
    {
        dealOwner.getBackpack().addProduct(responseProduct);
        responseProduct = null;
        responseHolder.getBackpack().addProduct(dealOwnerProduct);
    }

    public void discard(){
        responseHolder.getBackpack().addProduct(responseProduct);
    }
}
