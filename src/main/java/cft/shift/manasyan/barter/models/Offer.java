package cft.shift.manasyan.barter.models;

public class Offer {
    private Person seller;
    private Person buyer;
    private Product offeredProduct;
    private Product desiredProduct;
    private boolean isActive;

    public Offer(Person seller, Person buyer, Product offeredProduct, Product desiredProduct) {
        this.seller = seller;
        this.buyer = buyer;
        this.offeredProduct = offeredProduct;
        this.desiredProduct = desiredProduct;
        this.isActive = true;
    }

    public void closeOffer(){
        isActive = false;
    }

    public Product getOfferedProduct() {
        return offeredProduct;
    }

    public Product getDesiredProduct() {
        return desiredProduct;
    }
}
