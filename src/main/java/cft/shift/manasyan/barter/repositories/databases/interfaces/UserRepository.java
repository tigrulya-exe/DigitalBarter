package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.models.user.User;
import java.util.List;

public interface UserRepository {
    void addUser(User user);

    void deleteUser(String userId);

    List<User> getUsers();

    User getUser(String userId);

    User getUserByName(String userName);

    boolean contains(String userName);
}
