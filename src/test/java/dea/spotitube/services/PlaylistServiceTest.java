//package dea.spotitube.services;
//
//import nl.frej.dea.spotitube.services.PlaylistService;
//import nl.frej.dea.spotitube.services.dto.PlaylistResponseDTO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class PlaylistServiceTest {
//
//    private PlaylistService playlistService;
//
//    @BeforeEach
//    public void setUp() {
//        playlistService = new PlaylistService(playlistRepository);
//    }
//
//    @Test
//    public void whenPlaylistsNotEmpty_thenGetPlaylistResponseReturnsCorrectResponse() {
//        PlaylistResponseDTO response = playlistService.getPlaylistResponse();
//
//        assertNotNull(response, "Expected non-null response");
//
//        assertEquals(2, response.getPlaylists().size(), "Expected 2 playlists in the response");
//
//    }
//
//    @Test
//    public void whenPlaylistsEmpty_thenGetPlaylistResponseReturnsNull() {
//        playlistService.playlists.clear();
//
//        PlaylistResponseDTO response = playlistService.getPlaylistResponse();
//
//        assertNull(response, "Expected null response when playlists are empty");
//    }
//}
