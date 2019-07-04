package cft.shift.manasyan.barter.models.dtos;

public class ProductDTO {
    private String name;
    private String pictureUrl;
    private String type;

    public ProductDTO(){}

    public ProductDTO(String name, String pictureUrl, String type) {
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.type = type;
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
