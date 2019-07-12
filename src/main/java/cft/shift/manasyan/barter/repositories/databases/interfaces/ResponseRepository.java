package cft.shift.manasyan.barter.repositories.databases.interfaces;

import cft.shift.manasyan.barter.models.responses.DealResponse;

import java.util.List;

public interface ResponseRepository <T extends DealResponse> {
    T fetchResponse(String responseId);

    List<T> getAllResponses();

    void deleteResponse(String responseId);

    T createResponse(T response, String offerId);

    List<T> getAllUserResponses(String userId);

    List<T> getAllDealResponses(String offerId);
}
