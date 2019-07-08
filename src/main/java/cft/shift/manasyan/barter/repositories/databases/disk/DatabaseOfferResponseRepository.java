package cft.shift.manasyan.barter.repositories.databases.disk;

import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.repositories.databases.interfaces.OfferResponseRepository;

import java.util.Collection;
/*TODO write methods*/
public class DatabaseOfferResponseRepository implements OfferResponseRepository {
    @Override
    public DealResponse fetchResponse(String responseId) {
        return null;
    }

    @Override
    public Collection<DealResponse> getAllResponses() {
        return null;
    }

    @Override
    public DealResponse updateResponse(String holderId, String productId) {
        return null;
    }

    @Override
    public void deleteResponse(String responseId) {

    }

    @Override
    public DealResponse createResponse(String holderId, String productId) {
        return null;
    }

    @Override
    public Collection<DealResponse> getAllUserResponses(String userId) {
        return null;
    }

    @Override
    public Collection<DealResponse> getAllOfferResponses(String offerId) {
        return null;
    }
}
