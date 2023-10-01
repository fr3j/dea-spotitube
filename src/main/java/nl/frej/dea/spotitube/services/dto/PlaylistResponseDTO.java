package nl.frej.dea.spotitube.services.dto;

import java.util.ArrayList;

public class PlaylistResponseDTO {
    private ArrayList<PlaylistDTO> playlists;
    private Integer length;
    public void setPlaylists(ArrayList<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }
    public void setLength(Integer length) {
        this.length = length;
    }
    public ArrayList<PlaylistDTO> getPlaylists() {
        return playlists;
    }
    public Integer getLength() {
        return length;
    }
}
