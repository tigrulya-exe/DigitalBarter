package cft.shift.manasyan.barter.repositories.databases.disk;

import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.repositories.databases.interfaces.DealRepository;
import cft.shift.manasyan.barter.repositories.extractors.DesireExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
@Repository
@Qualifier("sqlDesires")
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseDesireRepository implements DealRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private DesireExtractor desireExtractor;
/*
    @PostConstruct
    public void initialize()
    {
        String createUserTableSql = "create table BARTER_DESIRES (" +
                "DESIRE_AND_PRODUCT_ID  VARCHAR(128),"+
                "HOLDER_ID     VARCHAR(128)," +
                "DESCRIPTION     VARCHAR(512)" +
                ");";
        jdbcTemplate.update(createUserTableSql, new MapSqlParameterSource());
    }
*/
    @Override
    public void addDeal(Deal deal) {
        Desire desire = (Desire)deal;
        String insertProductSql = "insert into BARTER_DESIRES (DESIRE_AND_PRODUCT_ID, HOLDER_ID, DESCRIPTION) values (:desireId, :holderId, :description)";
        MapSqlParameterSource desireParams = new MapSqlParameterSource()
                .addValue("desireId", desire.getId())
                .addValue("holderId", desire.getDealHolder())
                .addValue("description", desire.getDescription());
        jdbcTemplate.update(insertProductSql, desireParams);
    }

    @Override
    public void removeDeal(String dealId) {
        String deleteDesireSql = "delete from BARTER_DESIRES where DESIRE_AND_PRODUCT_ID=:desireId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("desireId", dealId);

        jdbcTemplate.update(deleteDesireSql, params);
    }

    @Override
    public List<Desire> getDeals() {
        String sql = "select BARTER_DESIRES.DESIRE_AND_PRODUCT_ID, HOLDER_ID, DESCRIPTION " +
                "from BARTER_DESIRES";

        return jdbcTemplate.query(sql, desireExtractor);
    }

    @Override
    public Desire getDeal(String dealId) {
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

    @Override
    public List<Desire> getUserDeals(String userId) {
        String sql = "select DESIRE_AND_PRODUCT_ID, BARTER_DESIRES.HOLDER_ID, DESCRIPTION "+
                "from BARTER_DESIRES "+
                "where BARTER_DESIRES.HOLDER_ID=:userId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);

        List<Desire> desires = jdbcTemplate.query(sql, params, desireExtractor);

        /*if(desires.isEmpty())
        {
            return null;
        }*/

        return desires;
    }
}
