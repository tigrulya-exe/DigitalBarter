package cft.shift.manasyan.barter.repositories.databases.disk;

import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.repositories.databases.interfaces.ResponseRepository;
import cft.shift.manasyan.barter.repositories.extractors.OfferResponseExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("offerResponses")
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseOfferResponseRepository implements ResponseRepository<DealResponse> {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private OfferResponseExtractor offerResponseExtractor;
/*
    @PostConstruct
    public void initialize()
    {
        String createUserTableSql = "create table BARTER_OFFER_RESPONSES (" +
                "RESPONSE_AND_PRODUCT_ID  VARCHAR(128),"+
                "HOLDER_ID     VARCHAR(128)," +
                "OFFER_ID     VARCHAR(128)" +
                ");";
        jdbcTemplate.update(createUserTableSql, new MapSqlParameterSource());
    }*/

    @Override
    public DealResponse fetchResponse(String responseId) {
        String sql = "select BARTER_OFFER_RESPONSES.RESPONSE_AND_PRODUCT_ID, HOLDER_ID, OFFER_ID "+
                "from BARTER_OFFER_RESPONSES "+
                "where BARTER_OFFER_RESPONSES.RESPONSE_AND_PRODUCT_ID=:responseId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("responseId", responseId);

        List<DealResponse> responses = jdbcTemplate.query(sql, params, offerResponseExtractor);

        if(responses.isEmpty())
        {
            return null;
        }
        return responses.get(0);
    }

    @Override
    public List<DealResponse> getAllResponses() {
       String sql = "select BARTER_OFFER_RESPONSES.RESPONSE_AND_PRODUCT_ID, HOLDER_ID, OFFER_ID "+
               "from BARTER_OFFER_RESPONSES";

       return jdbcTemplate.query(sql, offerResponseExtractor);
    }

    @Override
    public void deleteResponse(String responseId) {
        String deleteResponseSql = "delete from BARTER_OFFER_RESPONSES where RESPONSE_AND_PRODUCT_ID=:responseId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("responseId", responseId);

        jdbcTemplate.update(deleteResponseSql, params);
    }

    @Override
    public DealResponse createResponse(DealResponse response, String offerId) {
        String insertResponseSql = "insert into BARTER_OFFER_RESPONSES (RESPONSE_AND_PRODUCT_ID, HOLDER_ID, OFFER_ID) values (:responseId, :holderId, :offerId)";
        MapSqlParameterSource responseParams = new MapSqlParameterSource()
                .addValue("responseId", response.getId())
                .addValue("holderId", response.getResponseHolder().getId())
                .addValue("offerId", offerId);
        jdbcTemplate.update(insertResponseSql, responseParams);
        return response;
    }

    @Override
    public List<DealResponse> getAllUserResponses(String userId) {
        String sql = "select RESPONSE_AND_PRODUCT_ID, BARTER_OFFER_RESPONSES.HOLDER_ID, OFFER_ID "+
                "from BARTER_OFFER_RESPONSES "+
                "where HOLDER_ID=:userId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);

        List<DealResponse> responses = jdbcTemplate.query(sql, params, offerResponseExtractor);

        /*if(responses.isEmpty())
        {
            return null;
        }*/
        return responses;
    }

    @Override
    public List<DealResponse> getAllDealResponses(String offerId) {
        String sql = "select RESPONSE_AND_PRODUCT_ID, HOLDER_ID, BARTER_OFFER_RESPONSES.OFFER_ID "+
                "from BARTER_OFFER_RESPONSES "+
                "where OFFER_ID=:offerId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("offerId", offerId);

        List<DealResponse> responses = jdbcTemplate.query(sql, params, offerResponseExtractor);

        /*if(responses.isEmpty())
        {
            return null;
        }*/
        return responses;
    }
}
