package nl.frej.dea.spotitube.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import nl.frej.dea.spotitube.dao.interfaces.PlaylistDaoInterface;
import nl.frej.dea.spotitube.services.dto.PlaylistDTO;
import nl.frej.dea.spotitube.services.dto.TrackDTO;
import nl.frej.dea.spotitube.utils.DatabaseProperties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class PlaylistDaoImpl implements PlaylistDaoInterface {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DatabaseProperties databaseProperties;

    @Inject
    public PlaylistDaoImpl(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public PlaylistDaoImpl() {
    }


    @Override
    public Optional<PlaylistDTO> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<PlaylistDTO> findAll() {
        return null;
    }


    public List<PlaylistDTO> findPlaylists(String user) {

        List<PlaylistDTO> playlists = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT * from Playlist");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                PlaylistDTO playlist = new PlaylistDTO();
                playlist.setId(resultSet.getInt("id"));
                playlist.setName(resultSet.getString("name"));
                String owner = resultSet.getString("Owner");
                playlist.setOwner(owner.equals(user));
                playlist.setTracks(findTracks(playlist));
                playlists.add(playlist);
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
        return playlists;
    }

    @Override
    public void addPlaylist(String user, PlaylistDTO playlistDTO) {
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Playlist (id, name, Owner) VALUES (?, ?, ?)");

            statement.setInt(1, playlistDTO.getId());
            statement.setString(2, playlistDTO.getName());
            statement.setString(3, user);

            statement.executeUpdate();

            statement.close();
            connection.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }



    @Override
    public void save(PlaylistDTO playlistDTO) {

    }

    @Override
    public void update(PlaylistDTO playlistDTO, String[] params) {

    }

    @Override
    public void update(int id, String name, String owner){
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("UPDATE Playlist SET id = ?, name = ?, Owner = ? WHERE id = ?");

            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, owner);
            statement.setInt(4, id);

            statement.executeUpdate();

            statement.close();
            connection.close();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error communicating with database " + databaseProperties.connectionString(), e);
        }
    }

    @Override
    public void delete(PlaylistDTO playlistDTO) {

    }

    public ArrayList<TrackDTO> findTracks(PlaylistDTO playlistDTO) {
        ArrayList<TrackDTO> tracks = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(databaseProperties.connectionString());
            PreparedStatement statement = connection.prepareStatement("SELECT t.id as track_id, t.title, t.performer, t.duration, t.album, t.playcount, t.publicationDate, t.description, t.offlineAvailable FROM Track t JOIN Playlist_Track ptm ON t.id = ptm.trackId WHERE ptm.playlistId = ?");
            statement.setInt(1, playlistDTO.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TrackDTO track = new TrackDTO();
                track.setId(resultSet.getInt("track_id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("duration"));
                track.setAlbum(resultSet.getString("album"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setPublicationDate(resultSet.getTimestamp("publicationDate"));
                track.setDescription(resultSet.getString("description"));
                track.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));
                tracks.add(track);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error fetching tracks for playlist " + playlistDTO.getId(), e);
        }
        return tracks;
    }


}


