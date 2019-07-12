package cft.shift.manasyan.barter.services.helpers;

import cft.shift.manasyan.barter.models.Product;
import cft.shift.manasyan.barter.models.deals.Deal;

@FunctionalInterface
public interface DealPredicate {
     boolean isRelevant(Deal desire, Product product);
}
