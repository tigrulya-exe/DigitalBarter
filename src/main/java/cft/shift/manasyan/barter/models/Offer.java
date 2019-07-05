package cft.shift.manasyan.barter.models;

import cft.shift.manasyan.barter.models.dtos.OfferDTO;

import java.util.HashMap;

public class Offer extends Deal {
    private HashMap<String, OfferResponse> responses = null;/*list of responses to current offer*/

    public Offer(OfferDTO offerDTO, User user)
    {
        super(offerDTO, user);
    }
    public Offer(Product prod, User own, String description)
    {
        super(prod, own, description);
    }
    public void closeOffer(String responseId) {
        for(HashMap.Entry<String, OfferResponse> entry : responses.entrySet())
        {
            String key = entry.getKey();
            OfferResponse response = entry.getValue();
            if(key.equals(responseId))/*accept response with argument id*/
                response.accept(super.getDealHolder(), super.getDealProduct());
            else
                response.discard();
            responses.remove(key);/*delete response after accepting or discarding*/
        }
    }

    public String registerOfferResponse(User answerer, Product answererProduct) {
        try{
            if(answerer == null || answererProduct == null)
                throw new Exception();
        }
        catch(Exception e)
        {
            System.out.println("registerDealResponse had incorrect data");
            return null;
        }
        OfferResponse newResponse = new OfferResponse(answerer, answererProduct);
        responses.put(newResponse.getId(), newResponse);/*add new response to list of dtos of this offer*/
        return newResponse.getId();
    }
}
