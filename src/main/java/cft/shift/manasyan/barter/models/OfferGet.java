package cft.shift.manasyan.barter.models;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
/*class of type "I want it" */
public class OfferGet implements Offer {
    private Product ownerProduct = null;
    private Person owner = null;
    private HashMap<String, OfferResponse> responses = null;/*list of responses to current offer*/
    private String id = null;

    public OfferGet(Product prod, Person own)
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
            System.out.println("Bad data in OfferGet constructor");
        }
        id = own.getUid() + prod.getGlobalId();/*unique id - concatenation person id and product id */
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void closeOffer(String responseId) {
        for(HashMap.Entry<String, OfferResponse> entry : responses.entrySet())
        {
            String key = entry.getKey();
            OfferResponse resp = entry.getValue();
            if(key.equals(responseId))/*accept response with argument id*/
                resp.accept();
            else
                resp.discard();
            responses.remove(key);/*delete response after accepting or discarding*/
        }
    }

    @Override
    public Person getOfferHolder() {
        return owner;
    }

    @Override
    public Product getOfferHolderProduct() {
        return ownerProduct;
    }

    @Override
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
}
