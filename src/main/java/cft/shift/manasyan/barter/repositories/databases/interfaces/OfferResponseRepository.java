package cft.shift.manasyan.barter.repositories.databases.interfaces;

import cft.shift.manasyan.barter.models.responses.DealResponse;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface OfferResponseRepository {
    DealResponse fetchResponse(String responseId);

    Collection<DealResponse> getAllResponses();

    DealResponse updateResponse(String holderId, String productId);

    void deleteResponse(String responseId);

    DealResponse createResponse(String holderId, String productId);

    Collection<DealResponse> getAllUserResponses(String userId);

    Collection<DealResponse> getAllOfferResponses(String offerId);
}
