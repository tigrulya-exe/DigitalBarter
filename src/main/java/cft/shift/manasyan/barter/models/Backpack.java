package cft.shift.manasyan.barter.models;

import org.springframework.stereotype.Component;

import java.util.HashMap;


@Component
public class Backpack {
    private HashMap<String, Product> products;
    public Backpack()
    {
        this.products = new HashMap<>();
    }

    public boolean isProductInBackpack(String key)/*true if yes, false if no*/
    {
        return products.get(key) != null;
    }
    public Backpack(HashMap<String, Product> products){
        this.products = products;
    }

    public void addProduct(Product product){
        products.put(product.getId(), product);
    }

    public void deleteProduct(Product product){
        try {
            if (product == null)
                throw new Exception();
        }
        catch(Exception e)
        {
            System.out.println("deleteProduct incorrect argument");
        }
        products.remove(product.getId());
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }
}
