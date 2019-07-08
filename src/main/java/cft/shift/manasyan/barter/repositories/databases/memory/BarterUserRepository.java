package cft.shift.manasyan.barter.repositories.databases.memory;

import cft.shift.manasyan.barter.exceptions.NotFoundException;
import cft.shift.manasyan.barter.models.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BarterUserRepository
{
    private Map<String, User> users;
    public BarterUserRepository()
    {
        this.users = new HashMap<>();
    }
    public BarterUserRepository(Map<String, User>users)
    {
        this.users = users;
    }
    public void addUser(User user)
    {
        users.put(user.getUid(), user);
    }
    public void deleteUser(String userId)
    {

        if (users.remove(userId) == null)
        {
            throw new NotFoundException();
        }
    }
    public List<User> getUsers()
    {
        return (new ArrayList<>(users.values()));
    }
    public User getUser(String userId)
    {
        return users.get(userId);
    }

}
