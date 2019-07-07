package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.models.dtos.DesireTO;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DesireExtractor implements ResultSetExtractor<List<Desire>> {
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
                /*TODO think how to build desire with IDs*/
            }
        }
    }
}
