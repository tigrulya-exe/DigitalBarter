package cft.shift.manasyan.barter.models.dtos;

public class DesireDTO {
    private String description;
    private ProductDTO product;


    public DesireDTO(){}

    public DesireDTO(String description, ProductDTO product) {
        this.description = description;
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
