package nl.frej.dea.spotitube.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import nl.frej.dea.spotitube.dao.interfaces.PlaylistDaoInterface;
import nl.frej.dea.spotitube.dao.interfaces.TrackDaoInterface;
import nl.frej.dea.spotitube.dao.interfaces.UserDaoInterface;
import nl.frej.dea.spotitube.services.dto.PlaylistDTO;
import nl.frej.dea.spotitube.services.dto.PlaylistResponseDTO;
import nl.frej.dea.spotitube.services.dto.TrackDTO;

import java.util.List;
import java.util.Optional;

@ApplicationScoped

public class PlaylistService {
    @Inject
    private PlaylistDaoInterface playlistDao;

    @Inject
    private TrackDaoInterface trackDao;

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

    public void addTrack(String token, int id, TrackDTO trackDTO) {
        String user = userDao.getUserByToken(token);
        Optional<PlaylistDTO> optionalPlaylistDTO = playlistDao.get(id);
        if (optionalPlaylistDTO.isPresent()){
            PlaylistDTO playlistDTO = optionalPlaylistDTO.get();
            trackDao.addTrackToPlaylist(trackDTO, playlistDTO);
        }
        else {
            throw new NotFoundException();
        }
    }

    public List<TrackDTO> getTracksForPlaylist(int playlistId, String token) {
        String user = userDao.getUserByToken(token);
        Optional<PlaylistDTO> optionalPlaylistDTO = playlistDao.get(playlistId);
        if (optionalPlaylistDTO.isPresent()){
            PlaylistDTO playlistDTO = optionalPlaylistDTO.get();
            return playlistDao.findTracks(playlistDTO);
        }
        else {
            throw new NotFoundException();
        }
    }

    public void deletePlaylistName(String token, int id) {
        String user = userDao.getUserByToken(token);
        playlistDao.delete(id);
    }
}
