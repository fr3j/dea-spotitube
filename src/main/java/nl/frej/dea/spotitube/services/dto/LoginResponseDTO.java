package nl.frej.dea.spotitube.services.dto;

public class LoginResponseDTO {

    private String token;
    private String user;

    public LoginResponseDTO(String token, String user){
        this.token = token;
        this.user = user;
    }

    public String getUsername(){
        return this.user;
    }

    public String getToken(){
        return this.token;
    }
}
