package cft.shift.manasyan.barter.repositories.databases.disk;

import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.models.responses.DesireResponse;
import cft.shift.manasyan.barter.repositories.databases.interfaces.ResponseRepository;
import cft.shift.manasyan.barter.repositories.extractors.DesireResponseExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("desireResponses")
@ConditionalOnProperty(name = "use.database", havingValue = "true")
public class DatabaseDesireResponseRepository implements ResponseRepository<DesireResponse> {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private DesireResponseExtractor desireResponseExtractor;

    /*
    @PostConstruct
    public void initialize()
    {
        String createUserTableSql = "create table BARTER_DESIRE_RESPONSES (" +
                "RESPONSE_AND_PRODUCT_ID  VARCHAR(128),"+
                "HOLDER_ID     VARCHAR(128)," +
                "DESIRE_ID     VARCHAR(128)," +
                "DESIRE_PRODUCT_ID     VARCHAR(128)" +
                ");";
        jdbcTemplate.update(createUserTableSql, new MapSqlParameterSource());
    }*/

    @Override
    public DesireResponse fetchResponse(String responseId) {
        String sql = "select BARTER_OFFER_RESPONSES.RESPONSE_AND_PRODUCT_ID, HOLDER_ID, DESIRE_ID, DESIRE_PRODUCT_ID "+
                "from BARTER_OFFER_RESPONSES "+
                "where BARTER_OFFER_RESPONSES.RESPONSE_AND_PRODUCT_ID=:responseId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("responseId", responseId);

        List<DesireResponse> responses = jdbcTemplate.query(sql, params, desireResponseExtractor);

        if(responses.isEmpty())
        {
            return null;
        }
        return responses.get(0);
    }

    @Override
    public List<DesireResponse> getAllResponses() {
        String sql = "select BARTER_DESIRE_RESPONSES.RESPONSE_AND_PRODUCT_ID, HOLDER_ID, DESIRE_ID, DESIRE_PRODUCT_ID "+
                "from BARTER_DESIRE_RESPONSES";

        return jdbcTemplate.query(sql, desireResponseExtractor);
    }

    @Override
    public void deleteResponse(String responseId) {
        String deleteResponseSql = "delete from BARTER_DESIRE_RESPONSES where RESPONSE_AND_PRODUCT_ID=:responseId";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("responseId", responseId);

        jdbcTemplate.update(deleteResponseSql, params);
    }

    @Override
    public DesireResponse createResponse(DesireResponse response, String desireId) {
        String insertResponseSql = "insert into BARTER_DESIRE_RESPONSES (RESPONSE_AND_PRODUCT_ID, HOLDER_ID, DESIRE_ID, DESIRE_PRODUCT_ID) values (:responseId, :holderId, :desierId, :desireProductId)";
        MapSqlParameterSource responseParams = new MapSqlParameterSource()
                .addValue("responseId", response.getId())
                .addValue("holderId", response.getResponseHolder().getId())
                .addValue("offerId", desireId)
                .addValue("desireProductId", response.getDesiredProductResponse().getId());
        jdbcTemplate.update(insertResponseSql, responseParams);
        return response;
    }

    @Override
    public List<DesireResponse> getAllUserResponses(String userId) {
        String sql = "select RESPONSE_AND_PRODUCT_ID, BARTER_DESIRE_RESPONSES.HOLDER_ID, DESIRE_ID, DESIRE_PRODUCT_ID "+
                "from BARTER_DESIRE_RESPONSES "+
                "where HOLDER_ID=:userId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId);

        List<DesireResponse> responses = jdbcTemplate.query(sql, params, desireResponseExtractor);

        /*if(responses.isEmpty())
        {
            return null;
        }*/
        return responses;
    }

    public List<DesireResponse> getAllDealResponses(String desireId) {
        String sql = "select RESPONSE_AND_PRODUCT_ID, HOLDER_ID, BARTER_DESIRE_RESPONSES.DESIRE_ID, DESIRE_PRODUCT_ID "+
                "from BARTER_DESIRE_RESPONSES "+
                "where DESIRE_ID=:desireId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("desireId", desireId);

        List<DesireResponse> responses = jdbcTemplate.query(sql, params, desireResponseExtractor);

        /*if(responses.isEmpty())
        {
            return null;
        }*/
        return responses;
    }

    public DesireResponse updateDesireResponse(DesireResponse desireResponse, String desireId) {
        String updateDesireResponseSql = "update BARTER_DESIRE_RESPONSES "+
                "set HOLDER_ID=:userId, "+
                "DESIRE_ID=:desireId, "+
                "DESIRE_PRODUCT_ID=:desireProductId "+
                "where RESPONSE_AND_PRODUCT_ID=:responseId";
        MapSqlParameterSource productParams = new MapSqlParameterSource()
                .addValue("userId", desireResponse.getResponseHolder().getId())
                .addValue("desireId", desireId)
                .addValue("desireProductId", desireResponse.getDesiredProductResponse().getId())
                .addValue("responseId", desireResponse.getId());

        jdbcTemplate.update(updateDesireResponseSql, productParams);

        return desireResponse;
    }
}
