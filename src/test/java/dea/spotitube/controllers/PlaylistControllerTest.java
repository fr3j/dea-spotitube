//package dea.spotitube.controllers;
//
//import nl.frej.dea.spotitube.controllers.PlaylistController;
//import nl.frej.dea.spotitube.services.PlaylistService;
//import nl.frej.dea.spotitube.services.dto.PlaylistResponseDTO;
//import jakarta.ws.rs.core.Response;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class PlaylistControllerTest {
//
//    private PlaylistController playlistController;
//    private PlaylistService mockPlaylistService;
//
//    @BeforeEach
//    public void setUp() {
//        mockPlaylistService = mock(PlaylistService.class);
//        playlistController = new PlaylistController(mockPlaylistService);
//    }
//
////    @Test
////    public void ifPlaylistEmptyReturnError() {
////        when(mockPlaylistService.getPlaylistResponse()).thenReturn(null);
////
////        Response response = playlistController.getTracks();
////
////        assertEquals(400, response.getStatus(), "Expected HTTP 400 status");
////    }
////
////    @Test
////    public void ifPlaylistNotEmptyReturnSuccess() {
////        PlaylistResponseDTO mockResponse = new PlaylistResponseDTO();
////        when(mockPlaylistService.getPlaylistResponse()).thenReturn(mockResponse);
////
////        Response response = playlistController.getTracks();
////
////        assertEquals(200, response.getStatus(), "Expected HTTP 200 status");
////    }
//}
