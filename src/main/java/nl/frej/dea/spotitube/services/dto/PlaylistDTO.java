package nl.frej.dea.spotitube.services.dto;

import java.util.ArrayList;

public class PlaylistDTO {
    private int id;
    private String name;
    private boolean owner;
    private String user;
    private ArrayList<TrackDTO> tracks = new ArrayList<>();

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }


    public PlaylistDTO(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public void setTracks(ArrayList<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<TrackDTO> getTracks() {
        return tracks;
    }

    public boolean getOwner(){
        return this.owner;
    }

}
