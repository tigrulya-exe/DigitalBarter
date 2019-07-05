package cft.shift.manasyan.barter.models.deals;

import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.user.User;

import java.util.HashMap;
import java.util.Map;

/*class of offer. It could be "I want it" or "I want to change it for something"*/
public abstract class Deal {
    private Product dealProduct = null;
    private User dealHolder = null;
    private String id = null;
    private String description = null;

    private Map<String, DealResponse> responses;

    public Deal(Product prod, User own, String description)
    {
        this.dealHolder = own;
        dealHolder.getBackpack().deleteProduct(prod.getId());
        this.dealProduct = prod;
        this.description = description;
        this.id = prod.getId();/*unique id - concatenation person id and dealProduct id */
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
        return responses.get(responseId);
    }

    public Map<String, DealResponse> getResponses() {
        return responses;
    }

    protected abstract DealResponse createDealResponse(User user, Product product);


    public String registerResponse(User answerer, Product answererProduct) {
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
        return newResponse.getId();
    }

    public void closeDeal(String responseId) {
        for(Map.Entry<String, DealResponse> entry : responses.entrySet())
        {
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
