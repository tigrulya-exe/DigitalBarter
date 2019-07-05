package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.models.Deal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepository<T extends Deal> {
    void addDeal(T deal);

    void closeDeal(String dealId);

    List<T> getDeals();

    T getDeal(String dealId);
}