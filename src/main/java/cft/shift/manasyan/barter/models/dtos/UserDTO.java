package cft.shift.manasyan.barter.models.dtos;

public class UserDTO {
    private String name;
    private String profilePicUrl;
    private String biography = "";

    public UserDTO(String name, String profilePicUrl, String biography) {
        this.name = name;
        this.profilePicUrl = profilePicUrl;
        this.biography = biography;
    }

}
