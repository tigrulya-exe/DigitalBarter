package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.responses.DealResponse;
import lombok.NonNull;

public class ResponseTO {
    private String responseId;

    private UserTO responseHolder;

    private Product responseProduct;

    public ResponseTO(){}

    public ResponseTO(@NonNull DealResponse response) {
        this.responseHolder = new UserTO(response.getResponseHolder());
        this.responseId = response.getId();
        this.responseProduct = response.getResponseProduct();
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public Product getResponseProduct() {
        return responseProduct;
    }

    public void setResponseProduct(Product responseProduct) {
        this.responseProduct = responseProduct;
    }

    public UserTO getResponseHolder() {
        return responseHolder;
    }

    public void setResponseHolder(UserTO responseHolder) {
        this.responseHolder = responseHolder;
    }
}
