package cft.shift.manasyan.barter.models;

public interface Offer {
    String getId();

    void closeOffer(String responseId);

    Person getOfferHolder();

    Product getOfferHolderProduct();

    void registerOfferResponse();
}
