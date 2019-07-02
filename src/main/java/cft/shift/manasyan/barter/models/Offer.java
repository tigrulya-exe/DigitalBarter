package cft.shift.manasyan.barter.models;

public interface Offer {
    String getId();

    void closeOffer();

    Person getOfferHolder();

    Product getOfferHolderProduct();

    void registerOfferResponse();
}
