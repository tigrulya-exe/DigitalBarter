package cft.shift.manasyan.barter.models.user;

import cft.shift.manasyan.barter.models.deals.Desire;
import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.responses.DesireResponse;
import cft.shift.manasyan.barter.repositories.databases.disk.DatabaseDesireResponseRepository;
import cft.shift.manasyan.barter.repositories.databases.disk.DatabaseOfferResponseRepository;
import cft.shift.manasyan.barter.repositories.extractors.OfferResponseExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;


/*OK*/
public class UserResponses  {
    @Autowired
    @Qualifier("desireResponses")
    private DatabaseDesireResponseRepository databaseDesireResponses;

    @Autowired
    @Qualifier("offerResponses")
    private DatabaseOfferResponseRepository databaseOfferResponses;

    private List<DealResponse> offerResponses = new ArrayList<>();
    private List<DesireResponse> desireResponses = new ArrayList<>();

    public UserResponses(String userId){
        this.desireResponses = databaseDesireResponses.getAllUserResponses(userId);
        this.offerResponses = databaseOfferResponses.getAllUserResponses(userId);
    }

    public void addOfferResponse(DealResponse offerResponse, String offerId){
        offerResponses.add(offerResponse);
        databaseOfferResponses.createResponse(offerResponse, offerId);
    }

    public void addDesireResponse(DesireResponse desireResponse, String desireId){
        desireResponses.add(desireResponse);
        databaseDesireResponses.createResponse(desireResponse, desireId);
    }

    public void deleteOfferResponse(DealResponse offerResponse){
        offerResponses.remove(offerResponse);
        databaseOfferResponses.deleteResponse(offerResponse.getId());
    }

    public void deleteDesireResponse(DesireResponse desireResponse){
        desireResponses.remove(desireResponse);
        databaseDesireResponses.deleteResponse(desireResponse.getId());
    }

    public List<DealResponse> getOfferResponses() {
        return offerResponses;
    }

    public List<DesireResponse> getDesireResponses() {
        return desireResponses;
    }

    public List<DesireResponse> getConcreteDesireResponses(String desireId)
    {
        return databaseDesireResponses.getAllDealResponses(desireId);
    }
    public List<DealResponse> getConcreteOfferResponses(String offerId)
    {
        return databaseOfferResponses.getAllDealResponses(offerId);
    }
    public DesireResponse getDesireResponse(String responseId)
    {
        return databaseDesireResponses.fetchResponse(responseId);
    }
    public void updateDesireResponse(DesireResponse desireResponse, String desireId)
    {
        desireResponses.remove(desireResponse.getId());
        desireResponses.add(desireResponse);
        databaseDesireResponses.updateDesireResponse(desireResponse, desireId);
    }
}
