package nl.frej.dea.spotitube.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.frej.dea.spotitube.dao.PlaylistDaoInterface;
import nl.frej.dea.spotitube.services.dto.PlaylistDTO;
import nl.frej.dea.spotitube.services.dto.PlaylistResponseDTO;

import java.util.List;

@ApplicationScoped

public class PlaylistService {
    private PlaylistDaoInterface dao;

    @Inject
    public PlaylistService(PlaylistDaoInterface dao) {
        this.dao = dao;
    }

    public PlaylistService(){

    }

    public PlaylistResponseDTO getPlaylistResponse(String token) {
        List<PlaylistDTO> playlists = dao.findAll(token);
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


}
