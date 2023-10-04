package nl.frej.dea.spotitube.dao;

import nl.frej.dea.spotitube.services.dto.PlaylistDTO;

import java.util.List;

public interface PlaylistDaoInterface extends Dao<PlaylistDTO> {
    public List<PlaylistDTO> findAll(String token);

}
