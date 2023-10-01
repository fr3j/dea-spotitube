package nl.frej.dea.spotitube.services;

import nl.frej.dea.spotitube.services.dto.PlaylistDTO;
import nl.frej.dea.spotitube.services.dto.PlaylistResponseDTO;
import nl.frej.dea.spotitube.services.dto.TrackDTO;

import java.util.ArrayList;

public class PlaylistService {
    private ArrayList<PlaylistDTO> playlists = new ArrayList<>();

    public PlaylistService(){
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setId(1);
        playlistDTO.setName("Electronic playlist 2020");
        playlistDTO.setOwner(false);
        playlists.add(playlistDTO);
    }


    public PlaylistResponseDTO getPlaylistResponse() {
        PlaylistResponseDTO playlistResponseDTO = new PlaylistResponseDTO();
        playlistResponseDTO.setPlaylists(playlists);
        playlistResponseDTO.setLength(3600);
        return playlistResponseDTO;
    }
}
