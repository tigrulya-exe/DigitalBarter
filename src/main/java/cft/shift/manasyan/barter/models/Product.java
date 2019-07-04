package cft.shift.manasyan.barter.models;

import cft.shift.manasyan.barter.models.dtos.ProductDTO;

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

    private String pictureURL;

    public Product(ProductType type, String name, String condition, String picURL) {
        this.type = type;
        this.name = name;
        this.condition = condition;
        // и заглушку какую нить для фотачки
        this.pictureURL = picURL;
    }

    public Product (ProductDTO root)/*construct product by ProductDTO*/
    {
        this.pictureURL = root.getPicURL();
        this.name = root.getName();
        this.type = ProductType.valueOf(root.getType());
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

    public String getPictureURL() {
        return pictureURL;
    }

    public String getCondition() {
        return condition;
    }
    public void setType(ProductType newtype){type = newtype;}
    public void setName(String newname){name = newname;}
    public void setCondition(String newcond){condition = newcond;}
    public void setPictureURL(String newpic){pictureURL = newpic;}
}

