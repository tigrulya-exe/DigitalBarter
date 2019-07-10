package cft.shift.manasyan.barter.models.responses;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.user.User;
import org.springframework.web.bind.annotation.DeleteMapping;

public class DealResponse {
    private User responseHolder;
    private Product responseProduct;
    private String id;
    /*One deal can have only one response by one person. Thus response id is equal to holder id.*/
    public DealResponse(User responseHolder, Product responseProduct) {
        this.responseHolder = responseHolder;
        this.responseProduct = responseProduct;
        this.id = responseHolder.getId();

        registerResponse();
    }

    protected void registerResponse(){
        responseHolder.getUserResponses().addOfferResponse(this);
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

    public void accept(User dealOwner, Product dealOwnerProduct) {
        dealOwner.getBackpack().putProduct(responseProduct);
        responseHolder.getBackpack().putProduct(dealOwnerProduct);
    }

    public void discard(User dealOwner){
        responseHolder.getBackpack().putProduct(responseProduct);
    }

}
