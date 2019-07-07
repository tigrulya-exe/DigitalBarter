package cft.shift.manasyan.barter.models.deals;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.responses.DesireResponse;
import cft.shift.manasyan.barter.models.user.User;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/*class of offer. It could be "I want it" or "I want to change it for something"*/

public abstract class Deal {
    private Product dealProduct;
    private User dealHolder;
    private final String id;
    private String description;

    private Map<String, DealResponse> responses;

    public Deal(@NonNull Product product, @NonNull User user, @NonNull String description) {
        this.dealHolder = user;
        this.dealProduct = product;
        this.description = description;
        this.id = product.getId();/*unique id - concatenation person id and dealProduct id */
        this.responses = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public User getDealHolder() {
        return dealHolder;
    }

    public Product getDealProduct() {
        return dealProduct;
    }

    public DealResponse getDealResponse(String responseId){
        DealResponse dealResponse = getResponses().get(responseId);
        if(dealResponse == null)
            throw new NotFoundException("Wrong offerId");
        return dealResponse;    }

    public Map<String, DealResponse> getResponses() {
        return responses;
    }

    protected abstract DealResponse createDealResponse(@NonNull User user, @NonNull Product product);


    public DealResponse registerResponse(User answerer, Product answererProduct) {
        try{
            if(answerer == null || answererProduct == null)
                throw new Exception();
        }
        catch(Exception e)
        {
            System.out.println("registerDealResponse had incorrect data");
            return null;
        }

        DealResponse newResponse = createDealResponse(answerer,answererProduct);
        responses.put(newResponse.getId(), newResponse);/*add new response to list of dtos of this offer*/
        return newResponse;
    }

    public void closeDeal(String responseId) {
        if(responses.get(responseId) == null)
            throw new NotFoundException("Wrong responseId");

        for(Map.Entry<String, DealResponse> entry : responses.entrySet()) {
            String key = entry.getKey();
            DealResponse response = entry.getValue();
            if(key.equals(responseId))/*accept response with argument id*/
                response.accept(dealHolder, dealProduct);
            else
                response.discard();
            responses.remove(key);/*delete response after accepting or discarding*/
        }
    }
}
