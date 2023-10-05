package dea.spotitube.services;

import nl.frej.dea.spotitube.dao.interfaces.UserDaoInterface;
import nl.frej.dea.spotitube.services.UserService;
import nl.frej.dea.spotitube.services.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserDaoInterface userDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userDao);
    }

    @Test
    public void login_givenCorrectCredentials_returnsToken() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setUser("testUser");
        userDTO.setPassword("testPassword");
        when(userDao.getByUsername("testUser")).thenReturn(Optional.of(userDTO));

        // Act
        String token = userService.login(userDTO);

        // Assert
        assertNotNull(token);
        assertTrue(token.matches("[0-9]{4}-[0-9]{4}-[0-9]{4}")); // Ensuring the token is in the correct format
        verify(userDao).saveTokenToDatabase(token, "testUser");
    }

    @Test
    public void login_givenIncorrectCredentials_returnsNull() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setUser("testUser");
        userDTO.setPassword("wrongPassword");
        UserDTO storedUserDTO = new UserDTO();
        storedUserDTO.setUser("testUser");
        storedUserDTO.setPassword("testPassword");
        when(userDao.getByUsername("testUser")).thenReturn(Optional.of(storedUserDTO));

        // Act
        String token = userService.login(userDTO);

        // Assert
        assertNull(token);
    }

    @Test
    public void login_givenNonExistentUser_returnsNull() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        userDTO.setUser("nonExistentUser");
        userDTO.setPassword("somePassword");
        when(userDao.getByUsername("nonExistentUser")).thenReturn(Optional.empty());

        // Act
        String token = userService.login(userDTO);

        // Assert
        assertNull(token);
    }

    @Test
    public void generateToken_generatesCorrectTokenFormat() {
        // Act
        String token = userService.generateToken();

        // Assert
        assertTrue(token.matches("[0-9]{4}-[0-9]{4}-[0-9]{4}"));
    }

    // More tests can be added as necessary.
}

