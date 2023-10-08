package nl.frej.dea.spotitube.dao.interfaces;

import nl.frej.dea.spotitube.services.dto.PlaylistDTO;
import nl.frej.dea.spotitube.services.dto.TrackDTO;

public interface TrackDaoInterface extends Dao<TrackDTO> {
    void addTrackToPlaylist(TrackDTO trackDTO, PlaylistDTO playlistDTO);
}
