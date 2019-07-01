package cft.shift.manasyan.barter.models;

import java.util.UUID;

public class Product {
    // int for comparison speed
    private final int typeId;
    
    private final String globalId;
    private String name;
    private String pictureURL;

    public Product(){
        this.typeId = 1;
        this.globalId = UUID.randomUUID().toString();
    }

    public int getTypeId() {
        return typeId;
    }

    public String getGlobalId() {
        return globalId;
    }

    public String getName() {
        return name;
    }

    public String getPictureURL() {
        return pictureURL;
    }
}
