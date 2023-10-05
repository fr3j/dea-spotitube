package nl.frej.dea.spotitube.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.frej.dea.spotitube.services.PlaylistService;
import nl.frej.dea.spotitube.services.dto.PlaylistResponseDTO;

@Path("/playlists")
public class PlaylistController {
    private PlaylistService playlistService;
    @Inject
    public PlaylistController(PlaylistService playlistService)
    {
        this.playlistService = playlistService;
    }
    public PlaylistController() {
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("token") String token) {

        PlaylistResponseDTO playlists = playlistService.getPlaylistResponse(token);

        if (playlists == null) {
            return Response.status(400).build();
        } else {
            return Response.status(200).entity(playlists).build();
        }
    }


}
