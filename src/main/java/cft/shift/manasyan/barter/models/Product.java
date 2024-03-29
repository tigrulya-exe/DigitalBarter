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

    private final String id = UUID.randomUUID().toString();

    private String name;

    private String pictureURL;

    private String description;

    public Product(ProductType type, String name, String pictureURL, String description) {
        this.type = type;
        this.name = name;
        this.pictureURL = pictureURL;
        this.description = description;
    }

    //for Json
    public Product(){}

    public Product (ProductTO root)/*construct product by ProductTO*/ {
        this.pictureURL = root.getPictureURL();
        this.name = root.getName();
        this.type = ProductType.valueOf(root.getType());
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

