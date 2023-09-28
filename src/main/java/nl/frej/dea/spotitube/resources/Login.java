package nl.frej.dea.spotitube.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import nl.frej.dea.spotitube.services.UserService;
import nl.frej.dea.spotitube.services.dto.LoginResponseDTO;
import nl.frej.dea.spotitube.services.dto.UserDTO;
@Path("/login")
public class Login {
    public UserService userService = new UserService();
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginResponseDTO login(UserDTO userDTO) {
        String token = userService.login(userDTO);
        if (token == null){
            throw new RuntimeException("Authentication failed");
        }
        else {
            return new LoginResponseDTO(token, userDTO.getUsername());
        }
    }
}
