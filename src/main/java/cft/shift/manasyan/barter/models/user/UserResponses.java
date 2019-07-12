
package cft.shift.manasyan.barter.models.user;

import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.responses.DesireResponse;

import java.util.ArrayList;
import java.util.List;

public class UserResponses  {
    private List<DealResponse> offerResponses;
    private List<DesireResponse> desireResponses;

    public UserResponses(){
        this.desireResponses = new ArrayList<>();
        this.offerResponses = new ArrayList<>();
    }

    public void addOfferResponse(DealResponse offerResponse){
        offerResponses.add(offerResponse);
    }

    public void addDesireResponse(DesireResponse desireResponse){
        desireResponses.add(desireResponse);
    }

    public void deleteOfferResponse(DealResponse offerResponse){
        offerResponses.remove(offerResponse);
    }

    public void deleteDesireResponse(DesireResponse desireResponse){
        desireResponses.remove(desireResponse);
    }

    public List<DealResponse> getOfferResponses() {
        return offerResponses;
    }

    public List<DesireResponse> getDesireResponses() {
        return desireResponses;
    }
}