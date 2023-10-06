package nl.frej.dea.spotitube.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.frej.dea.spotitube.dao.interfaces.PlaylistDaoInterface;
import nl.frej.dea.spotitube.dao.interfaces.UserDaoInterface;
import nl.frej.dea.spotitube.services.dto.PlaylistDTO;
import nl.frej.dea.spotitube.services.dto.PlaylistResponseDTO;

import java.util.List;

@ApplicationScoped

public class PlaylistService {
    @Inject
    private PlaylistDaoInterface playlistDao;

    @Inject
    private UserDaoInterface userDao;

    public PlaylistService(PlaylistDaoInterface playlistDao, UserDaoInterface userDao) {
        this.playlistDao = playlistDao;
        this.userDao = userDao;
    }

    public PlaylistService() {
    }


    public PlaylistResponseDTO getPlaylistResponse(String token) {
        String user = userDao.getUserByToken(token);
        List<PlaylistDTO> playlists = playlistDao.findPlaylists(user);
        if (!playlists.isEmpty()) {
            PlaylistResponseDTO playlistResponseDTO = new PlaylistResponseDTO();
            playlistResponseDTO.setPlaylists(playlists);
            playlistResponseDTO.setLength(3600);
            return playlistResponseDTO;
        }
        else {
            return null;
        }
    }


    public void addPlaylist(String token, PlaylistDTO playlistDTO) {
        String user = userDao.getUserByToken(token);
        playlistDao.addPlaylist(user, playlistDTO);
    }

    public void editPlaylistName(String token, int id, PlaylistDTO playlistDTO) {
        String user = userDao.getUserByToken(token);
        String playlistName = playlistDTO.getName();
        playlistDao.update(id, playlistName, user);
    }
}
