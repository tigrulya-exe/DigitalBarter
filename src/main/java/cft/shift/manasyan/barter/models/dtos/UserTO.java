package cft.shift.manasyan.barter.models.dtos;

import cft.shift.manasyan.barter.models.user.User;
import lombok.NonNull;

public class UserTO {
    private String userId;
    private String name;

    UserTO(){}

    public UserTO(@NonNull User user) {
        this.userId = user.getId();
        this.name = user.getName();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
