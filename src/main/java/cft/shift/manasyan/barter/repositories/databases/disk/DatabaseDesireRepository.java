package cft.shift.manasyan.barter.repositories.databases.disk;

import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.repositories.databases.interfaces.DealRepository;
import cft.shift.manasyan.barter.repositories.extractors.DesireExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseDesireRepository implements DealRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private DesireExtractor desireExtractor;

    @Override
    public void addDeal(Deal deal) {

    }

    @Override
    public void closeDeal(String dealId) {

    }

    @Override
    public List<Desire> getDeals() {
        return null;
    }

    @Override
    public Deal getDeal(String dealId) {
        String sql = "select BARTER_DESIRES.DESIRE_AND_PRODUCT_ID, HOLDER_ID, DESCRIPTION "+
                "from BARTER_DESIRES "+
                "where BARTER_DESIRES.DESIRE_AND_PRODUCT_ID=:desireId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("desireId", dealId);

        List<Desire> desires = jdbcTemplate.query(sql, params, desireExtractor);

        if(desires.isEmpty())
        {
            return null;
        }
        return desires.get(0);
    }
}
