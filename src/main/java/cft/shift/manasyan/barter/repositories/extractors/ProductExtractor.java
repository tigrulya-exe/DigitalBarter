package cft.shift.manasyan.barter.repositories.extractors;

import cft.shift.manasyan.barter.models.Product;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductExtractor implements ResultSetExtractor<List<Product>> {

    @Override
    public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Product> products = new HashMap<>();

        while(rs.next())
        {
            String productId = rs.getString("PRODUCT_ID");

            Product product;
            if(products.containsKey(productId))
            {
                product = products.get(productId);
            }
            else
            {
                product = new Product(rs.getString("TYPE"),
                                        rs.getString("USER_ID"),
                                        rs.getString("NAME"),
                                        rs.getString("PICTURE_URL"),
                                        rs.getString("DESCRIPTION"),
                                        rs.getString("PRODUCT_ID"));
            }
            products.put(productId, product);
        }
        return new ArrayList<>(products.values());
    }
}
