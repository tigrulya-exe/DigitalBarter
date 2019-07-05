package cft.shift.manasyan.barter.models;

import cft.shift.manasyan.barter.models.dtos.DesireDTO;
import cft.shift.manasyan.barter.models.dtos.OfferDTO;

import java.util.HashMap;
/*class of offer. It could be "I want it" or "I want to change it for something"*/
public abstract class Deal {
    private Product dealProduct = null;
    private User dealHolder = null;
    private String id = null;
    private String description = null;

    public Deal(Product prod, User own, String description)
    {
        this.dealHolder = own;
        dealHolder.getBackpack().deleteProduct(prod.getId());
        this.dealProduct = prod;
        this.description = description;
        id = prod.getId();/*unique id - concatenation person id and dealProduct id */
    }

    public Deal(OfferDTO offerDTO, User user)
    {
        this.dealProduct = user.getBackpack().getProduct(offerDTO.getProductId());
        this.dealHolder = user;
        this.description = offerDTO.getDescription();
        this.id = dealProduct.getId();

        dealHolder.getUserDeals().addOffer(this);
    }

    public Deal(DesireDTO desireDTO, User user)
    {
        this.dealProduct = new Product(desireDTO.getProduct());
        this.dealHolder = user;
        this.description = desireDTO.getDescription();
        this.id = dealProduct.getId();

        dealHolder.getUserDeals().addDesire(this);
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
}
