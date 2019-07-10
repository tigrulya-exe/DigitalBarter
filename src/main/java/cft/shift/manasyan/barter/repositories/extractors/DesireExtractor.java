package cft.shift.manasyan.barter.repositories.extractors;

import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.repositories.databases.disk.DatabaseProductRepository;
import cft.shift.manasyan.barter.repositories.databases.disk.DatabaseUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DesireExtractor implements ResultSetExtractor<List<Desire>> {
    @Autowired
    private DatabaseProductRepository databaseProductRepository;
    @Autowired
    private DatabaseUserRepository databaseUserRepository;

    @Override
    public List<Desire> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Desire> desires = new HashMap<>();

        while(rs.next())
        {
            String desireId = rs.getString("DESIRE_AND_PRODUCT_ID");/*equals to productId*/

            Desire desire;
            if(desires.containsKey(desireId))
            {
                desire = desires.get(desireId);
            }
            else
            {
                desire = new Desire(databaseProductRepository.getProduct(rs.getString("DESIRE_AND_PRODUCT_ID")),
                                    databaseUserRepository.getUser(rs.getString("HOLDER_ID")),
                                    rs.getString("DESCRIPTION"));
            }
            desires.put(desireId, desire);
        }
        return new ArrayList<>(desires.values());
    }
}
