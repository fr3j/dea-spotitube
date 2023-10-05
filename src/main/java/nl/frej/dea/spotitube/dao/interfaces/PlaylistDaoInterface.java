package nl.frej.dea.spotitube.dao.interfaces;

import nl.frej.dea.spotitube.services.dto.PlaylistDTO;

import java.util.List;

public interface PlaylistDaoInterface extends Dao<PlaylistDTO> {
    public List<PlaylistDTO> findPlaylists(String token);

}
