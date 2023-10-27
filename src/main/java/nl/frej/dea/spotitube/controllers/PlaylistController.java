package nl.frej.dea.spotitube.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import nl.frej.dea.spotitube.services.PlaylistService;
import nl.frej.dea.spotitube.services.dto.PlaylistResponseDTO;
import nl.frej.dea.spotitube.services.dto.TrackDTO;
import nl.frej.dea.spotitube.services.dto.PlaylistDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/playlists")
public class PlaylistController {
    private PlaylistService playlistService;

    @Inject
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }


    public PlaylistController(){}

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response getPlaylists(@QueryParam("token") String token) {
        PlaylistResponseDTO playlists = playlistService.getPlaylistResponse(token);

        if (playlists == null) {
            return Response.status(400).build();
        } else {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String json = objectMapper.writeValueAsString(playlists);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return Response.status(200).entity(playlists).build();
        }
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksForPlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        List<TrackDTO> tracks = playlistService.getTracksForPlaylist(id, token);
        if (tracks == null) {
            return Response.status(400).build();
        } else {
            Map<String, List<TrackDTO>> response = new HashMap<>();
            response.put("tracks", tracks);
            return Response.status(200).entity(response).build();
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

    @DELETE
    @Path("/{id}")
    public Response removePlaylist(@QueryParam("token") String token, @PathParam("id") int id){
        playlistService.deletePlaylistName(token, id);
        return Response.status(200).entity(playlistService.getPlaylistResponse(token)).build();
    }

}


