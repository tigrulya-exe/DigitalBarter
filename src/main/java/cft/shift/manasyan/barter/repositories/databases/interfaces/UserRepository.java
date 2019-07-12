package cft.shift.manasyan.barter.repositories.databases.interfaces;

import cft.shift.manasyan.barter.models.user.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository {
    User getUser(String userId);

    List<User> getUsers();

    User updateUser(User user);

    void deleteUser(String userId);

    void addUser(User user);

    User getUserByName(String name);

    //boolean contains(String userName);
}
