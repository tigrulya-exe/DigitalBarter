package cft.shift.manasyan.barter.repositories.databases.interfaces;

import cft.shift.manasyan.barter.models.responses.DesireResponse;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DesireResponseRepository {
    DesireResponse fetchResponse(String ResponseId);

    Collection<DesireResponse> getAllResponses();

    void deleteResponse(String responseId);

    DesireResponse createResponse(DesireResponse response, String desireId);

    Collection<DesireResponse> getAllUserResponses(String userId);

    Collection<DesireResponse> getAllDesireResponses(String desireId);
}
