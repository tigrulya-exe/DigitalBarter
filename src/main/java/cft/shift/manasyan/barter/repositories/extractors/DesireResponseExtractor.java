package cft.shift.manasyan.barter.repositories.extractors;

import cft.shift.manasyan.barter.models.responses.DesireResponse;
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
public class DesireResponseExtractor implements ResultSetExtractor<List<DesireResponse>> {
    @Autowired
    private DatabaseUserRepository databaseUserRepository;

    @Autowired
    private DatabaseProductRepository databaseProductRepository;

    @Override
    public List<DesireResponse> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, DesireResponse> responses = new HashMap<>();

        while(rs.next())
        {
            String responseId = rs.getString("RESPONSE_AND_PRODUCT_ID");

            DesireResponse response;
            if(responses.containsKey(responseId))
            {
                response = responses.get(responseId);
            }
            else
            {
                response = new DesireResponse(databaseUserRepository.getUser(rs.getString("HOLDER_ID")),
                                    databaseProductRepository.getProduct(responseId),
                                    databaseProductRepository.getProduct("DESIRED_PRODUCT_ID"));
            }
            responses.put(responseId, response);
        }
        return new ArrayList<>(responses.values());
    }
}
