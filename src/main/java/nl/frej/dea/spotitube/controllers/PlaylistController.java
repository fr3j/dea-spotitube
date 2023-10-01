package nl.frej.dea.spotitube.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.frej.dea.spotitube.services.PlaylistService;
import nl.frej.dea.spotitube.services.dto.PlaylistDTO;
import nl.frej.dea.spotitube.services.dto.PlaylistResponseDTO;
import nl.frej.dea.spotitube.services.dto.TrackDTO;

import java.util.ArrayList;

@Path("/playlists")
public class PlaylistController {
    private PlaylistService playlistService = new PlaylistService();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(){
        PlaylistResponseDTO playlists = playlistService.getPlaylistResponse();
        if (playlists == null){
            return Response.status(400).build();
        }
        else {
            return Response.status(200).entity(playlists).build();
        }
    }

//    @Inject
//    public void setPlaylistService(PlaylistService playlistService) {
//        this.playlistService = playlistService;
//    }


}
