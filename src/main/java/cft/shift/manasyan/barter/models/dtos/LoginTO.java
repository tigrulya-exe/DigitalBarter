package cft.shift.manasyan.barter.models.dtos;

public class LoginTO {
    private String userId;

    public LoginTO(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


}
