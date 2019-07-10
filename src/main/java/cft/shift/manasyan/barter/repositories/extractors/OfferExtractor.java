package cft.shift.manasyan.barter.repositories.extractors;

import cft.shift.manasyan.barter.models.deals.Offer;
import cft.shift.manasyan.barter.models.dtos.OfferTO;
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
public class OfferExtractor implements ResultSetExtractor<List<Offer>> {
    @Autowired
    private DatabaseUserRepository databaseUserRepository;

    @Override
    public List<Offer> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String, Offer> offers = new HashMap<>();

        while(rs.next())
        {
            String offerId = rs.getString("OFFER_AND_PRODUCT_ID");

            Offer offer;
            if(offers.containsKey(offerId))
            {
                offer = offers.get(offerId);
            }
            else
            {
                offer = new Offer(new OfferTO(rs.getString("DESCRIPTION"), offerId),
                        databaseUserRepository.getUser(rs.getString("HOLDER_ID")));
            }
            offers.put(offerId, offer);
        }
        return new ArrayList<>(offers.values());
    }
}
