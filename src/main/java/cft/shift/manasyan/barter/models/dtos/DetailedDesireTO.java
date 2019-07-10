package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.responses.DealResponse;
import cft.shift.manasyan.barter.models.responses.DesireResponse;

import java.util.List;

public class DetailedDesireTO  extends DetailedDealTO<DesireResponseTO>{

    public DetailedDesireTO(Deal deal) {
        super(deal);
    }

    @Override
    protected void initResponses(List<DealResponse> dealResponses) {
        for(DealResponse response : dealResponses){
            getResponses().add(new DesireResponseTO((DesireResponse)response));
        }
    }
}
