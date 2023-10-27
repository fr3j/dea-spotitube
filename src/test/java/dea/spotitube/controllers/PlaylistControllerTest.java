package dea.spotitube.controllers;

import jakarta.ws.rs.core.Response;
import nl.frej.dea.spotitube.controllers.PlaylistController;
import nl.frej.dea.spotitube.services.PlaylistService;
import nl.frej.dea.spotitube.services.dto.PlaylistDTO;
import nl.frej.dea.spotitube.services.dto.PlaylistResponseDTO;
import nl.frej.dea.spotitube.services.dto.TrackDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public void getTracksForPlaylist_givenValidTokenAndId_returnsTracks() {
        String token = "validToken";
        int playlistId = 1;
        List<TrackDTO> tracks = new ArrayList<>();
        when(playlistService.getTracksForPlaylist(playlistId, token)).thenReturn(tracks);

        Response response = playlistController.getTracksForPlaylist(playlistId, token);

        assertEquals(200, response.getStatus());
        assertEquals(tracks, ((Map) response.getEntity()).get("tracks"));
    }

    @Test
    public void getTracksForPlaylist_givenInvalidTokenOrId_returnsBadRequestResponse() {
        String token = "invalidToken";
        int playlistId = 999;
        when(playlistService.getTracksForPlaylist(playlistId, token)).thenReturn(null);

        Response response = playlistController.getTracksForPlaylist(playlistId, token);

        assertEquals(400, response.getStatus());
    }

    @Test
    public void addPlaylist_givenValidTokenAndDTO_addsPlaylist() {
        String token = "validToken";
        PlaylistDTO playlistDTO = new PlaylistDTO();

        when(playlistService.getPlaylistResponse(token)).thenReturn(new PlaylistResponseDTO());

        Response response = playlistController.addPlaylist(token, playlistDTO);

        assertEquals(200, response.getStatus());
    }

    @Test
    public void editPlaylistName_givenValidTokenAndId_editsPlaylist() {
        String token = "validToken";
        int playlistId = 1;
        PlaylistDTO playlistDTO = new PlaylistDTO();

        when(playlistService.getPlaylistResponse(token)).thenReturn(new PlaylistResponseDTO());

        Response response = playlistController.editPlaylistName(token, playlistId, playlistDTO);

        assertEquals(200, response.getStatus());
    }

    @Test
    public void removePlaylist_givenValidTokenAndId_removesPlaylist() {
        String token = "validToken";
        int playlistId = 1;

        when(playlistService.getPlaylistResponse(token)).thenReturn(new PlaylistResponseDTO());

        Response response = playlistController.removePlaylist(token, playlistId);

        assertEquals(200, response.getStatus());
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
