package cft.shift.manasyan.barter.repositories.databases.interfaces;

import cft.shift.manasyan.barter.models.responses.DealResponse;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OfferResponseRepository {
    DealResponse fetchResponse(String responseId);

    Collection<DealResponse> getAllResponses();

    void deleteResponse(String responseId);

    DealResponse createResponse(DealResponse response, String offerId);

    Collection<DealResponse> getAllUserResponses(String userId);

    Collection<DealResponse> getAllOfferResponses(String offerId);
}
