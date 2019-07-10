package cft.shift.manasyan.barter.repositories.databases.memory;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.repositories.databases.interfaces.DealRepository;

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
    public void removeDeal(String dealId) {
        if (deals.remove(dealId) == null){
            throw new NotFoundException("Wrong dealId");
        }
    }

    @Override
    public List<T> getDeals() {
        return new ArrayList<>(deals.values());
    }

    @Override
    public T getDeal(String dealId) {
        Deal deal = deals.get(dealId);
        if(deal == null)
            throw new NotFoundException("Wrong dealId");
        return deals.get(dealId);
    }


}
