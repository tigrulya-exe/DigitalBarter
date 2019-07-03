package cft.shift.manasyan.barter.models;

import java.util.HashMap;
/*class of offer. It could be "I want it" or "I want to change it for something"*/
public class Offer {
    private Product ownerProduct = null;
    private Person owner = null;
    private HashMap<String, OfferResponse> responses = null;/*list of responses to current offer*/
    private String id = null;

    public Offer(Product prod, Person own)
    {
        ownerProduct = prod;
        owner = own;
        try/*checking of correction of data*/
        {
            if (owner == null || ownerProduct == null)
                throw new Exception();
        }
        catch(Exception e)
        {
            System.out.println("Bad data in Offer constructor");
        }
        id = own.getUid() + prod.getId();/*unique id - concatenation person id and product id */
    }


    public String getId() {
        return id;
    }


    public void closeOffer(String responseId) {
        for(HashMap.Entry<String, OfferResponse> entry : responses.entrySet())
        {
            String key = entry.getKey();
            OfferResponse resp = entry.getValue();
            if(key.equals(responseId))/*accept response with argument id*/
                resp.accept(owner, ownerProduct);
            else
                resp.discard();
            responses.remove(key);/*delete response after accepting or discarding*/
        }
    }


    public Person getOfferHolder() {
        return owner;
    }


    public Product getOfferProduct() {
        return ownerProduct;
    }


    public void registerOfferResponse(Person answerer, Product answererProduct)
    {
        try{
            if(answerer == null || answererProduct == null)
                throw new Exception();
        }
        catch(Exception e)
        {
            System.out.println("registerOfferResponse had incorrect data");
        }
        OfferResponse newResponse = new OfferResponse(answerer, answererProduct);
        responses.put(newResponse.getId(), newResponse);/*add new response to list of responses of this offer*/
    }

    public OfferResponse getOfferResponse(String responseId){
        return responses.get(responseId);
    }
}
