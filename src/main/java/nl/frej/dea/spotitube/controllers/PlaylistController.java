package nl.frej.dea.spotitube.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.frej.dea.spotitube.services.PlaylistService;
import nl.frej.dea.spotitube.services.dto.PlaylistDTO;
import nl.frej.dea.spotitube.services.dto.PlaylistResponseDTO;
import nl.frej.dea.spotitube.services.dto.TrackDTO;

@Path("/playlists")
public class PlaylistController {
    private PlaylistService playlistService;

    @Inject
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    public PlaylistController() {
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {

        PlaylistResponseDTO playlists = playlistService.getPlaylistResponse(token);

        if (playlists == null) {
            return Response.status(400).build();
        } else {
            return Response.status(200).entity(playlists).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistDTO playlistDTO) {
        playlistService.addPlaylist(token, playlistDTO);
        return Response.status(200).entity(playlistService.getPlaylistResponse(token)).build();
    }

    @PUT
    @Path("/{id}")
    public Response editPlaylistName(@QueryParam("token") String token, @PathParam("id") int id, PlaylistDTO playlistDTO){
        playlistService.editPlaylistName(token, id, playlistDTO);
        return Response.status(200).entity(playlistService.getPlaylistResponse(token)).build();
    }

    @POST
    @Path("/{id}/tracks")
    public Response addTrack(@QueryParam("token") String token, @PathParam("id") int id, TrackDTO trackDTO){
        playlistService.addTrack(token, id, trackDTO);
        return null;
    }
}


