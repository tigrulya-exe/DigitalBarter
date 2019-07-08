package cft.shift.manasyan.barter.repositories.databases.disk;


import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.repositories.databases.interfaces.ProductRepository;
import cft.shift.manasyan.barter.repositories.extractors.ProductExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseProductRepository  implements ProductRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private ProductExtractor productExtractor;

    @Autowired
    private DatabaseUserRepository databaseUserRepository;
    /*закомментить эту штуку, когда бд создастся (будет выкидывать ошибки при следующих
    *  запусках, при существующей таблице)*/
    /*@PostConstruct
    public void initialize() {
        deleteProduct("f39ed94a-57fb-4c4e-a069-ff7bfd289449");
    }*/

    @Override
    public Product fetchProduct(String productId) {
        String sql = "select BARTER_PRODUCTS.PRODUCT_ID, USER_ID, NAME, DESCRIPTION, TYPE, PIC_URL " +
                "from BARTER_PRODUCTS " +
                "where BARTER_PRODUCTS.PRODUCT_ID=:productId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("productId", productId);

        List<Product> products = jdbcTemplate.query(sql, params, productExtractor);

        if (products.isEmpty()) {
            return null;
        }
        return products.get(0);
    }

    @Override
    public Collection<Product> fetchUserProducts(String userId) {
        /*TODO test it please*/
        String sql = "select PRODUCT_ID, BARTER_PRODUCTS.USER_ID, NAME, DESCRIPTION, TYPE, PIC_URL " +
                "from BARTER_PRODUCTS " +
                "where BARTER_PRODUCTS.USER_ID=:userId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);

        List<Product> products = jdbcTemplate.query(sql, params, productExtractor);

        if (products.isEmpty()) {
            return null;
        }
        return products;
    }

    @Override
    public Collection<Product> getAllProducts() {
        String sql = "select BARTER_PRODUCTS.PRODUCT_ID, USER_ID, NAME, DESCRIPTION, TYPE, PIC_URL " +
        "from BARTER_PRODUCTS";

        return jdbcTemplate.query(sql, productExtractor);
    }

    @Override
    public Product updateProduct(String productId, Product product) {
        String updateProductSql = "update BARTER_PRODUCTS "+
                "set USER_ID=:userId, "+
                "NAME=:name, "+
                "DESCRIPTION=:description, "+
                "TYPE=:type, "+
                "PIC_URL=:pic_url"+
                "where PRODUCT_ID=:productId";
        MapSqlParameterSource productParams = new MapSqlParameterSource()
                .addValue("userId", product.getUserID())
                .addValue("name", product.getName())
                .addValue("description", product.getDescription())
                .addValue("type", product.getType().toString())
                .addValue("pic_url", product.getPictureURL())
                .addValue("productId", productId);

        jdbcTemplate.update(updateProductSql, productParams);

        return product;
    }

    @Override
    public void deleteProduct(String productId) {
        String deleteProductSql = "delete from BARTER_PRODUCTS where PRODUCT_ID=:productId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("productId", productId);

        jdbcTemplate.update(deleteProductSql, params);
    }

    @Override
    public Product createProduct(Product product) {
        String insertProductSql = "insert into BARTER_PRODUCTS (PRODUCT_ID, USER_ID, NAME, DESCRIPTION, TYPE, PIC_URL) values (:productId, :userId, :name, :description, :type, :pic_url)";
        MapSqlParameterSource productParams = new MapSqlParameterSource()
                .addValue("productId", product.getId())
                .addValue("userId", product.getUserID())
                .addValue("name", product.getName())
                .addValue("description", product.getDescription())
                .addValue("type", product.getType().toString())
                .addValue("pic_url", product.getPictureURL());
        jdbcTemplate.update(insertProductSql, productParams);
        return product;
    }
}
