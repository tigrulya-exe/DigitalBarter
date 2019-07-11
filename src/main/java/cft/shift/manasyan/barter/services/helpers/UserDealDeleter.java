package cft.shift.manasyan.barter.services.helpers;

import cft.shift.manasyan.barter.models.deals.Deal;
import cft.shift.manasyan.barter.models.user.User;

@FunctionalInterface
public interface UserDealDeleter {
    public void delete(User user, Deal deal);
}
