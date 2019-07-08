package cft.shift.manasyan.barter.models;

import cft.shift.manasyan.barter.models.dtos.ProductTO;

import java.util.UUID;

public class Product {

    public enum ProductType{
        FOOD,
        FURNITURE,
        CLOTHES,
        TECHNIQUE,
        // TODO: make types non discrete (just String)
    }

    private ProductType type;

    private String userID;

    private String id = UUID.randomUUID().toString();

    private String name;

    private String pictureURL;

    private String description = "It is dead nice product.";

    public Product(ProductType type, String name, String pictureURL, String descript) {
        this.type = type;
        this.name = name;
        this.pictureURL = pictureURL;
        this.description = descript;
    }

    public Product(String type, String userID, String name, String pictureURL, String description, String id) {
        this.type = ProductType.valueOf(type);
        this.userID = userID;
        this.name = name;
        this.pictureURL = pictureURL;
        this.description = description;
        this.id = id;
    }
    public Product(String type, String userID, String name, String pictureURL, String description) {
        this.type = ProductType.valueOf(type);
        this.userID = userID;
        this.name = name;
        this.pictureURL = pictureURL;
        this.description = description;
        this.id = id;
    }

    public Product(ProductType type, String name, String pictureURL) {
        this.type = type;
        this.name = name;
        this.pictureURL = pictureURL;
    }

    public Product (ProductTO root)/*construct product by ProductTO*/
    {
        this.pictureURL = root.getPictureUrl();
        this.name = root.getName();
        this.type = ProductType.valueOf(root.getType());
    }

    public String getUserID() {
        return userID;
    }

    public String getDescription() {
        return description;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

}

