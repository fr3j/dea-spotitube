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
        UserDTO userDTO = new UserDTO();
        userDTO.setUser("testUser");
        userDTO.setPassword("testPassword");
        when(userDao.getByUsername("testUser")).thenReturn(Optional.of(userDTO));

        String token = userService.login(userDTO);

        assertNotNull(token);
        assertTrue(token.matches("[0-9]{4}-[0-9]{4}-[0-9]{4}"));
        verify(userDao).saveTokenToDatabase(token, "testUser");
    }

    @Test
    public void login_givenIncorrectCredentials_returnsNull() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser("testUser");
        userDTO.setPassword("wrongPassword");
        UserDTO storedUserDTO = new UserDTO();
        storedUserDTO.setUser("testUser");
        storedUserDTO.setPassword("testPassword");
        when(userDao.getByUsername("testUser")).thenReturn(Optional.of(storedUserDTO));

        String token = userService.login(userDTO);

        assertNull(token);
    }

    @Test
    public void login_givenNonExistentUser_returnsNull() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser("nonExistentUser");
        userDTO.setPassword("somePassword");
        when(userDao.getByUsername("nonExistentUser")).thenReturn(Optional.empty());

        String token = userService.login(userDTO);

        assertNull(token);
    }

    @Test
    public void generateToken_generatesCorrectTokenFormat() {
        String token = userService.generateToken();

        assertTrue(token.matches("[0-9]{4}-[0-9]{4}-[0-9]{4}"));
    }

}

