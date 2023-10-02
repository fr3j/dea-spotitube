package nl.frej.dea.spotitube.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.frej.dea.spotitube.services.UserService;
import nl.frej.dea.spotitube.services.dto.LoginResponseDTO;
import nl.frej.dea.spotitube.services.dto.UserDTO;
@Path("/login")
public class LoginController {
    private UserService userService;

    public LoginController() { }
    @Inject
    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO) {
        String token = userService.login(userDTO);
        if (token == null){
            return Response.status(400).build();
        }
        else {
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setToken(token);
            loginResponseDTO.setUser(userDTO.getUser());
            return Response.status(200).entity(loginResponseDTO).build();
        }
    }
}
