package nl.frej.dea.spotitube.services.dto;

public class LoginResponseDTO {

    private String token;
    private String user;

//    public LoginResponseDTO(String token, String user){
//        this.token = token;
//        this.user = user;
//    }


    public String getUser(){
        return this.user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }
}
