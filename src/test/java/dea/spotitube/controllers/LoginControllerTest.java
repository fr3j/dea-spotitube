package dea.spotitube.controllers;

import jakarta.ws.rs.core.Response;
import nl.frej.dea.spotitube.controllers.LoginController;
import nl.frej.dea.spotitube.services.UserService;
import nl.frej.dea.spotitube.services.dto.LoginResponseDTO;
import nl.frej.dea.spotitube.services.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class LoginControllerTest {

    private LoginController loginController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loginController = new LoginController(userService);
    }

    @Test
    public void login_givenValidCredentials_returnsResponseWithToken() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser("testUser");
        userDTO.setPassword("testPassword");
        String token = "1234-5678-9012";
        when(userService.login(userDTO)).thenReturn(token);


        Response response = loginController.login(userDTO);

        assertEquals(200, response.getStatus());
        LoginResponseDTO responseBody = (LoginResponseDTO) response.getEntity();
        assertEquals(token, responseBody.getToken());
        assertEquals("testUser", responseBody.getUser());
    }

    @Test
    public void login_givenInvalidCredentials_returnsBadRequestResponse() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser("testUser");
        userDTO.setPassword("wrongPassword");
        when(userService.login(userDTO)).thenReturn(null);

        Response response = loginController.login(userDTO);

        assertEquals(400, response.getStatus());
    }

    // More tests can be added as necessary, such as for null userDTO, other edge cases, etc.
}
