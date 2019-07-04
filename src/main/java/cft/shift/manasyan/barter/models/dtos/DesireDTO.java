package cft.shift.manasyan.barter.models.dtos;

public class DesireDTO {
    private String description;
    private ProductDTO product;

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
}
