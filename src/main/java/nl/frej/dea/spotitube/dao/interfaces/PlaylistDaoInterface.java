package nl.frej.dea.spotitube.dao.interfaces;

import nl.frej.dea.spotitube.services.dto.PlaylistDTO;

import java.util.List;

public interface PlaylistDaoInterface extends Dao<PlaylistDTO> {
    List<PlaylistDTO> findPlaylists(String token);

    void addPlaylist(String token, PlaylistDTO playlistDTO);

    void update(int id, String name, String owner);
}
