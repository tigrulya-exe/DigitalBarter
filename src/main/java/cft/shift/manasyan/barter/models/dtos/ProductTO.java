package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.Product;

public class ProductTO {
    private String name;
    private String pictureUrl;
    private String type;

    public ProductTO(){}

    public ProductTO(String name, String pictureUrl, String type) {
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.type = type;
    }

    public ProductTO(Product product)
    {
        name = product.getName();
        pictureUrl = product.getPictureURL();
        type = product.getType().toString();
    }

    public String getName() {
        return name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setType(String type) {
        this.type = type;
    }


}
