package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.deals.Deal;

import java.util.*;

//@Repository
public class BarterDealRepository<T extends Deal> implements DealRepository<T> {

    private Map<String, T> deals;

    public BarterDealRepository(){
        this.deals = new HashMap<>();
    }

    private BarterDealRepository(Map<String, T> deals){
        this.deals = deals;
    }

    @Override
    public void addDeal(T deal) {
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
    public List<T> getDeals() {
        return new ArrayList<>(deals.values());
    }

    @Override
    public T getDeal(String dealId) {
        return deals.get(dealId);
    }


}
