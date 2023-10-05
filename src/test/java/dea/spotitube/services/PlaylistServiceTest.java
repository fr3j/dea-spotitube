package dea.spotitube.services;

import nl.frej.dea.spotitube.dao.interfaces.PlaylistDaoInterface;
import nl.frej.dea.spotitube.dao.interfaces.UserDaoInterface;
import nl.frej.dea.spotitube.services.PlaylistService;
import nl.frej.dea.spotitube.services.dto.PlaylistDTO;
import nl.frej.dea.spotitube.services.dto.PlaylistResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class PlaylistServiceTest {

    private PlaylistService playlistService;

    @Mock
    private PlaylistDaoInterface playlistDao;

    @Mock
    private UserDaoInterface userDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        playlistService = new PlaylistService(playlistDao, userDao);
    }

    @Test
    public void getPlaylistResponse_givenTokenWithPlaylists_returnsResponse() {
        // Arrange
        String token = "sampleToken";
        String user = "sampleUser";
        List<PlaylistDTO> playlists = Arrays.asList(
                new PlaylistDTO(),
                new PlaylistDTO()
        );
        when(userDao.getUserByToken(token)).thenReturn(user);
        when(playlistDao.findPlaylists(user)).thenReturn(playlists);

        // Act
        PlaylistResponseDTO result = playlistService.getPlaylistResponse(token);

        // Assert
        assertEquals(3600, result.getLength());
        assertEquals(playlists, result.getPlaylists());
    }

    @Test
    public void getPlaylistResponse_givenTokenWithNoPlaylists_returnsNull() {
        // Arrange
        String token = "sampleToken";
        String user = "sampleUser";
        when(userDao.getUserByToken(token)).thenReturn(user);
        when(playlistDao.findPlaylists(user)).thenReturn(Collections.emptyList());

        // Act
        PlaylistResponseDTO result = playlistService.getPlaylistResponse(token);

        // Assert
        assertNull(result);
    }

    // Add more tests as necessary, e.g., for error cases, etc.
}
