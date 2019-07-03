package cft.shift.manasyan.barter.models;

import java.net.URL;
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

    //TODO put in context
    private String condition;

    private URL pictureURL;

    public Product(ProductType type, String name, String condition, URL picURL) {
        this.type = type;
        this.name = name;
        this.condition = condition;
        // и заглушку какую нить для фотачки
        this.pictureURL = picURL;
    }

    public ProductType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public URL getPictureURL() {
        return pictureURL;
    }

    public String getCondition() {
        return condition;
    }
}

