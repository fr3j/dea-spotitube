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
        String token = "validToken";
        PlaylistResponseDTO playlistResponse = new PlaylistResponseDTO();
        when(playlistService.getPlaylistResponse(token)).thenReturn(playlistResponse);

        Response response = playlistController.getPlaylists(token);

        assertEquals(200, response.getStatus());
        assertEquals(playlistResponse, response.getEntity());
    }

    @Test
    public void getTracks_givenInvalidToken_returnsBadRequestResponse() {
        String token = "invalidToken";
        when(playlistService.getPlaylistResponse(token)).thenReturn(null);
        Response response = playlistController.getPlaylists(token);
        assertEquals(400, response.getStatus());
    }
}
