package cft.shift.manasyan.barter.repositories.databases.memory;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.user.User;
import cft.shift.manasyan.barter.repositories.databases.interfaces.UserRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Qualifier("ram")
public class BarterUserRepository implements UserRepository {
    private Map<String, User> users;

    public BarterUserRepository() {
        this.users = new HashMap<>();
    }

    public BarterUserRepository(Map<String, User> users) {
        this.users = users;
    }

    public void addUser(@NonNull User user) {
        users.put(user.getId(), user);
    }

    @Override
    public User getUserByName(String name) {
        return null;
    }

    public void deleteUser(String userId) {
        if (users.remove(userId) == null)
            throw new NotFoundException("Wrong userId");
    }
    public List<User> getUsers()
    {
        return (new ArrayList<>(users.values()));
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    public User getUser(String userId) {
        User user = users.get(userId);
        if (user == null)
            throw new NotFoundException("Wrong userId");
        return users.get(userId);
    }

//    @Override
//    public boolean contains(@NonNull String userName) {
//
//        for(User user : users.values()){
//            if(userName.equals(user.getName()))
//                return true;
//        }
//
//        return false;
//    }

}
