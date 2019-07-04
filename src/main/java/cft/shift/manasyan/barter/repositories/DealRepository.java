package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.models.Deal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepository {
    void addDeal(Deal deal);

    void closeDeal(String dealId);

    List<Deal> getDeals();

    Deal getDeal(String dealId);
}
