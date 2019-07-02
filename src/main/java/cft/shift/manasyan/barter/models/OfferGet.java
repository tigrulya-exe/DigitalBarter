package cft.shift.manasyan.barter.models;

import java.util.Iterator;
import java.util.List;
/*class of type "I want it" */
public class OfferGet implements Offer {
    private Product ownerProduct = null;
    private Person owner = null;
    private List<OfferResponse> responses = null;
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
        for(OfferResponse resp : responses)
        {
            if(resp.getId() == responseId)
                resp
        }
    }

    @Override
    public Person getOfferHolder() {
        return null;
    }

    @Override
    public Product getOfferHolderProduct() {
        return null;
    }

    @Override
    public void registerOfferResponse() {

    }
}
