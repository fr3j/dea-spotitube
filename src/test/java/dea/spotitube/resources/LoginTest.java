package dea.spotitube.resources;

import nl.frej.dea.spotitube.resources.Login;
import nl.frej.dea.spotitube.services.UserService;
import nl.frej.dea.spotitube.services.dto.UserDTO;
import nl.frej.dea.spotitube.services.dto.LoginResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginTest {

    private Login login;
    private UserService mockUserService;

    @BeforeEach
    void setUp() {
        mockUserService = mock(UserService.class);
        login = new Login();
        login.userService = mockUserService; // Injecting the mock into your class
    }

    @Test
    void loginReturnsValidTokenAndUsername() {
        UserDTO dummyUser = new UserDTO("testUser", "testPassword");
        when(mockUserService.login(dummyUser)).thenReturn("testToken"); // Mocking behavior

        LoginResponseDTO response = login.login(dummyUser);

        assertEquals("testUser", response.getUsername());
        assertEquals("testToken", response.getToken());
    }

    @Test
    void loginThrowsExceptionWhenNoToken() {
        UserDTO dummyUser = new UserDTO("testUser", "testPassword");
        when(mockUserService.login(dummyUser)).thenReturn(null); // Mocking behavior

        assertThrows(RuntimeException.class, () -> {
            login.login(dummyUser);
        });
    }
}
