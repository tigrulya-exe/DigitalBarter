package cft.shift.manasyan.barter.repositories;

import cft.shift.manasyan.barter.models.user.User;

import java.util.Collection;

public interface UserRepository {
    User fetchUser(String userId);

    Collection<User> getAllUsers();

    User updateUser(String userId, String name, User user);

    void deleteUser(String userId);

    User createUser(String name, User user);
}
