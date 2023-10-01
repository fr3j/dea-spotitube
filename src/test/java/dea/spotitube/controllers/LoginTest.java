package dea.spotitube.controllers;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import nl.frej.dea.spotitube.controllers.Login;
import nl.frej.dea.spotitube.services.UserService;
import nl.frej.dea.spotitube.services.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;
import jakarta.ws.rs.core.Response;

    public class LoginTest {

        private UserService mockUserService;
        private Login loginController;

        @Before
        public void setUp() {
            mockUserService = mock(UserService.class);
            loginController = new Login(mockUserService);
        }

        @Test
        public void testLoginSuccess() {
            UserDTO userDTO = new UserDTO();
            userDTO.setUser("testUser");
            when(mockUserService.login(userDTO)).thenReturn("testToken");

            Response response = loginController.login(userDTO);

            assertEquals(200, response.getStatus());
        }

        @Test
        public void testLoginFailure() {
            UserDTO userDTO = new UserDTO();
            when(mockUserService.login(userDTO)).thenReturn(null);

            Response response = loginController.login(userDTO);

            assertEquals(400, response.getStatus());
        }
    }


