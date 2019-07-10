package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.responses.DealResponse;

import java.util.ArrayList;
import java.util.List;

public class DetailedDealTO <T extends ResponseTO> extends DealTO {

    private List<T> responses;

    public DetailedDealTO(Deal deal) {
        super(deal);
        this.responses = new ArrayList<>();
        initResponses(new ArrayList<>(deal.getResponses().values()));
    }

    protected void initResponses(List<DealResponse> dealResponses) {
        for(DealResponse response : dealResponses){
            responses.add( (T) new ResponseTO(response));
        }
    }

    public List<T> getResponses() {
        return responses;
    }

    public void setResponses(List<T> responses) {
        this.responses = responses;
    }
}
