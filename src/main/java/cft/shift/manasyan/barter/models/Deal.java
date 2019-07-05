package cft.shift.manasyan.barter.models;

import cft.shift.manasyan.barter.models.dtos.DesireDTO;
import cft.shift.manasyan.barter.models.dtos.OfferDTO;

import java.util.HashMap;
/*class of offer. It could be "I want it" or "I want to change it for something"*/
public class Deal {
    private Product dealProduct = null;
    private User dealHolder = null;
    private HashMap<String, DealResponse> responses = null;/*list of responses to current offer*/
    private String id = null;
    private String description = null;
    public enum DealType {
        DESIRE,
        OFFER
    }

    private DealType type;

    public Deal(Product prod, User own, DealType type, String description)
    {
        this.type = type;
        this.dealHolder = own;
        dealHolder.getBackpack().deleteProduct(prod.getId());
        this.dealProduct = prod;
        this.description = description;

        try/*checking of correction of data*/
        {
            if (dealHolder == null || dealProduct == null)
                throw new Exception();
        }
        catch(Exception e)
        {
            System.out.println("Bad data in Deal constructor");
        }
        id = prod.getId();/*unique id - concatenation person id and dealProduct id */
    }

    public Deal(OfferDTO offerDTO, User user)
    {
        this.dealProduct = user.getBackpack().getProduct(offerDTO.getProductId());
        this.dealHolder = user;
        this.description = offerDTO.getDescription();
        this.type = DealType.OFFER;
        this.id = dealProduct.getId();

        dealHolder.getUserDeals().addOffer(this);
    }

    public Deal(DesireDTO desireDTO, User user)
    {
        this.dealProduct = new Product(desireDTO.getProduct());
        this.dealHolder = user;
        this.description = desireDTO.getDescription();
        this.type = DealType.DESIRE;
        this.id = dealProduct.getId();

        dealHolder.getUserDeals().addDesire(this);
    }

    public DealType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void closeDeal(String responseId) {
        for(HashMap.Entry<String, DealResponse> entry : responses.entrySet())
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

    public String getDescription() {
        return description;
    }

    public User getDealHolder() {
        return dealHolder;
    }


    public Product getDealProduct() {
        return dealProduct;
    }


    public String registerDealResponse(User answerer, Product answererProduct)
    {
        try{
            if(answerer == null || answererProduct == null)
                throw new Exception();
        }
        catch(Exception e)
        {
            System.out.println("registerDealResponse had incorrect data");
            return null;
        }

        // TODO DIVIDE DEAL INTO DESIRE AND OFFER
        // tmp solution
        DealResponse newResponse = (type == DealType.DESIRE) ? new DesireResponse(answerer, answererProduct) : new DealResponse(answerer, answererProduct);
        responses.put(newResponse.getId(), newResponse);/*add new response to list of dtos of this offer*/
        return newResponse.getId();
    }

    public DealResponse getDealResponse(String responseId){
        return responses.get(responseId);
    }
}
