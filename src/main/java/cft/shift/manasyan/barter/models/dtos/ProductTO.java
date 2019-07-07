package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.Product;

public class ProductTO {
    private String name;
    private String pictureURL;
    private String type;

    public ProductTO(){}

    public ProductTO(String name, String pictureURL, String type) {
        this.name = name;
        this.pictureURL = pictureURL;
        this.type = type;
    }

    public ProductTO(Product product)
    {
        name = product.getName();
        pictureURL = product.getPictureURL();
        type = product.getType().toString();
    }

    public String getName() {
        return name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public void setType(String type) {
        this.type = type;
    }


}
