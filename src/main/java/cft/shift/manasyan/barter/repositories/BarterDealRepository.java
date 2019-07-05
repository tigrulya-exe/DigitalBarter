package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.Deal;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class BarterDealRepository implements DealRepository {

    private Map<String, Deal> deals;

    public BarterDealRepository(){
        this.deals = new HashMap<>();
    }

    private BarterDealRepository(Map<String, Deal> deals){
        this.deals = deals;
    }

    @Override
    public void addDeal(Deal deal) {
        deals.put(deal.getId(), deal);
        System.out.println("dealId = [" + deal.getId() + "] was added");
    }

    @Override
    public void closeDeal(String dealId) {

        if (deals.remove(dealId) == null){
            throw new NotFoundException();
        }
    }

    @Override
    public List<Deal> getDeals() {
        return new ArrayList<>(deals.values());
    }

    @Override
    public Deal getDeal(String dealId) {
        return deals.get(dealId);
    }


}
