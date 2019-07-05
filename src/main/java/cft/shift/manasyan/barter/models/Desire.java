package cft.shift.manasyan.barter.models;

import cft.shift.manasyan.barter.models.dtos.DesireDTO;

import java.util.HashMap;

public class Desire extends Deal {
    private HashMap<String, DesireResponse> responses = null;/*list of responses to current offer*/
    public Desire(DesireDTO desireDTO, User user)
    {
            super(desireDTO, user);
    }
    public Desire(Product prod, User own, String description)
    {
        super(prod, own, description);
    }
    public DesireResponse getDesireResponse(String responsId)
    {
        return responses.get(responsId);
    }
    public void closeDesire(String responseId) {
        for(HashMap.Entry<String, DesireResponse> entry : responses.entrySet())
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

    public String registerDesireResponse(User answerer, Product answererProduct) {
        try{
            if(answerer == null || answererProduct == null)
                throw new Exception();
        }
        catch(Exception e)
        {
            System.out.println("registerDealResponse had incorrect data");
            return null;
        }

        DesireResponse newResponse = new DesireResponse(answerer, answererProduct);
        responses.put(newResponse.getId(), newResponse);/*add new response to list of dtos of this offer*/
        return newResponse.getId();
    }
}
