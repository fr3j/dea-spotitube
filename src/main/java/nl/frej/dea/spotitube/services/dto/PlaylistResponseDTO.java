package nl.frej.dea.spotitube.services.dto;

import java.util.List;

public class PlaylistResponseDTO {
    private List<PlaylistDTO> playlists;
    private Integer length;
    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }
    public void setLength(Integer length) {
        this.length = length;
    }
    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }
    public Integer getLength() {
        return length;
    }
}
