package cft.shift.manasyan.barter.repositories.databases.disk;

import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.models.deals.Offer;
import cft.shift.manasyan.barter.repositories.databases.interfaces.DealRepository;
import cft.shift.manasyan.barter.repositories.extractors.DesireExtractor;
import cft.shift.manasyan.barter.repositories.extractors.OfferExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseOfferRepository implements DealRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private OfferExtractor offerExtractor;
/*
        @PostConstruct
        public void initialize()
        {
            String createUserTableSql = "create table BARTER_OFFERS (" +
                    "OFFER_AND_PRODUCT_ID  VARCHAR(128),"+
                    "HOLDER_ID     VARCHAR(128)," +
                    "DESCRIPTION     VARCHAR(512)" +
                    ");";
            jdbcTemplate.update(createUserTableSql, new MapSqlParameterSource());
        }*/

    @Override
    public void addDeal(Deal deal) {
        Offer offer = (Offer)deal;
        String insertProductSql = "insert into BARTER_OFFERS (OFFER_AND_PRODUCT_ID, HOLDER_ID, DESCRIPTION) values (:offerId, :holderId, :description)";
        MapSqlParameterSource offerParams = new MapSqlParameterSource()
                .addValue("offerId", offer.getId())
                .addValue("holderId", offer.getDealHolder())
                .addValue("description", offer.getDescription());
        jdbcTemplate.update(insertProductSql, offerParams);
    }

    @Override
    public void closeDeal(String dealId) {
        String deleteOfferSql = "delete from BARTER_OFFERS where OFFER_AND_PRODUCT_ID=:offerId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("offerId", dealId);

        jdbcTemplate.update(deleteOfferSql, params);
    }

    @Override
    public List<Offer> getDeals() {
        String sql = "select BARTER_OFFERS.OFFER_AND_PRODUCT_ID, HOLDER_ID, DESCRIPTION " +
                "from BARTER_OFFERS";

        return jdbcTemplate.query(sql, offerExtractor);
    }

    @Override
    public Deal getDeal(String dealId) {
        String sql = "select BARTER_OFFERS.OFFER_AND_PRODUCT_ID, HOLDER_ID, DESCRIPTION "+
                "from BARTER_OFFERS "+
                "where BARTER_OFFERS.OFFER_AND_PRODUCT_ID=:offerId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("offerId", dealId);

        List<Offer> offers = jdbcTemplate.query(sql, params, offerExtractor);

        if(offers.isEmpty())
        {
            return null;
        }

        return offers.get(0);
    }
}
