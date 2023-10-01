package nl.frej.dea.spotitube.services.dto;

public class UserDTO {
    private String user;
    private String password;

    public UserDTO(String user, String password){
        this.user = user;
        this.password = password;
    }

    public UserDTO(){
        this.user = null;
        this.password = null;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
