package cft.shift.manasyan.barter.models.dtos;

public class ProductDTO {
    private String name;
    private String picURL;
    private String type;

    public ProductDTO(String name, String picURL, String type) {
        this.name = name;
        this.picURL = picURL;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getPicURL() {
        return picURL;
    }

    public String getType() {
        return type;
    }
}
