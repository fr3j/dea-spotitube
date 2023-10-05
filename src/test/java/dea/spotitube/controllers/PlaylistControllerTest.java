package dea.spotitube.controllers;

import jakarta.ws.rs.core.Response;
import nl.frej.dea.spotitube.controllers.PlaylistController;
import nl.frej.dea.spotitube.services.PlaylistService;
import nl.frej.dea.spotitube.services.dto.PlaylistResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PlaylistControllerTest {

    private PlaylistController playlistController;

    @Mock
    private PlaylistService playlistService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        playlistController = new PlaylistController(playlistService);
    }

    @Test
    public void getTracks_givenValidToken_returnsResponseWithPlaylists() {
        // Arrange
        String token = "validToken";
        PlaylistResponseDTO playlistResponse = new PlaylistResponseDTO();
        when(playlistService.getPlaylistResponse(token)).thenReturn(playlistResponse);

        // Act
        Response response = playlistController.getTracks(token);

        // Assert
        assertEquals(200, response.getStatus());
        assertEquals(playlistResponse, response.getEntity());
    }

    @Test
    public void getTracks_givenInvalidToken_returnsBadRequestResponse() {
        // Arrange
        String token = "invalidToken";
        when(playlistService.getPlaylistResponse(token)).thenReturn(null);

        // Act
        Response response = playlistController.getTracks(token);

        // Assert
        assertEquals(400, response.getStatus());
    }

    // You can add more tests as necessary, such as for null tokens, other edge cases, etc.
}
